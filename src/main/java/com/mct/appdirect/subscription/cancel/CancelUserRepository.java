package com.mct.appdirect.subscription.cancel;

import java.util.Optional;

@FunctionalInterface
interface CancelUserRepository {

    Optional<String> cancelUserAndReturnErrorIfPresent(String accountIdentifier);
}
