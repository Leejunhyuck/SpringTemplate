package org.raccoon.com.jwt.user.graphql;

import graphql.ExecutionResult;

public interface GraphUseCase {
    ExecutionResult execute(String query);
}