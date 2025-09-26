package com.gtarp.tabaricobackend.exception;

public class RewardNotFoundException extends NotFoundException {
    public RewardNotFoundException(int id) { super("Reward with id " + id + " not found"); }
}
