package services;

import dataaccess.exceptions.BadRequestException;

public class Service {
    void checkForBadRequest(String... requestFields) throws BadRequestException {
        for (String requestField: requestFields) {
            if (requestField == null) {
                throw new BadRequestException("A field was missing");
            }
        }
    }
}
