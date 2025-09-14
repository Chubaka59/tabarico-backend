package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.UserDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.AccountingRebootDate;
import com.gtarp.tabaricobackend.exception.*;
import com.gtarp.tabaricobackend.repositories.UserRepository;
import com.gtarp.tabaricobackend.repositories.accounting.AccountingRebootDateRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import com.gtarp.tabaricobackend.services.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl extends AbstractCrudService<User, UserRepository, UserDto> implements UserService {

    public UserServiceImpl(UserRepository repository){
        super(repository);
    }

    @Autowired
    private AccountingRebootDateRepository accountingRebootDateRepository;

    @Override
    public User getById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User insert(UserDto userDto) {
        Optional<User> existingUser = this.repository.findUserByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException(userDto.getUsername());
        }
        User newUser = new User(userDto);
        if (userDto.getIdentityCardImage() != null && !userDto.getIdentityCardImage().isEmpty()) {
            newUser.setIdentityCardImage(saveIdentityCardImage(userDto.getIdentityCardImage(), newUser.getUsername()));
        }
        return this.repository.save(newUser);
    }

    public User getByUsername(String username) {
        return this.repository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User update(Integer id, UserDto userDto) {
        User updatedUser = getById(id).update(userDto);
        if (userDto.getIdentityCardImage() != null && !userDto.getIdentityCardImage().isEmpty()) {
            updatedUser.setIdentityCardImage(saveIdentityCardImage(userDto.getIdentityCardImage(), updatedUser.getUsername()));
        }
        return repository.save(updatedUser);
    }

    @Override
    public void updatePassword(String username, String password) {if (password == null || password.isEmpty()) {
            throw new EmptyPasswordException(username);
        }
        User updatedEntity = getByUsername(username).updatePassword(password);
        repository.save(updatedEntity);
    }

    @Value("${app.upload.dir}")
    private String uploadDir;

    private String saveIdentityCardImage(MultipartFile file, String username) {
        String[] imageFormat = new String[]{"jpeg", "png", "webp"};
        Path destinationFile;
        String fileName;

        if (file == null || file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }
        try {
            if (Arrays.stream(imageFormat).noneMatch(file.getContentType()::contains)) {
                throw new FileTypeException(file.getContentType() + "is not a valid type of file");
            }

            // Générer un nom de fichier lié a l'utilisateur
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.lastIndexOf(".") != -1) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            fileName = "CarteIdentite_" + username + fileExtension;

            Path location = Path.of(uploadDir);

            // Crée le répertoire s'il n'existe pas
            if (!Files.exists(location)) {
                Files.createDirectories(location);
            }

            destinationFile = location.resolve(fileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        return fileName;
    }

    @Transactional
    public void resetWeeklyUserAccounting() {
        AccountingRebootDate accountingRebootDate = accountingRebootDateRepository.findAll().get(0);
        accountingRebootDate.setAccountingRebootDate(LocalDateTime.now());
        accountingRebootDateRepository.save(accountingRebootDate);
        List<User> userList = repository.findAll();
        for (User user : userList) {
            if (user.isQuota() && user.isExporterQuota()) {
                user.setCleanMoneySalaryPreviousWeek(user.getCleanMoneySalary());
                user.setDirtyMoneySalaryPreviousWeek(user.getDirtyMoneySalary());
            } else {
                user.setCleanMoneySalaryPreviousWeek(0);
                user.setDirtyMoneySalaryPreviousWeek(0);
            }
            user.setCleanMoneySalary(0);
            user.setDirtyMoneySalary(0);
            user.setQuota(false);
            user.setExporterQuota(false);
        }
    }
}
