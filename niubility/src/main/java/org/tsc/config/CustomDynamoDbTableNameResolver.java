package org.tsc.config;

import io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class CustomDynamoDbTableNameResolver implements DynamoDbTableNameResolver {

    @Nullable
    private final String tablePrefix;

    public CustomDynamoDbTableNameResolver(@Nullable String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public CustomDynamoDbTableNameResolver() {
        this(null);
    }

    @Override
    public String resolve(Class clazz) {
        Assert.notNull(clazz, "clazz is required");

        String prefix = StringUtils.hasText(tablePrefix) ? tablePrefix : "";
        return prefix.concat(clazz.getSimpleName().replaceAll("(.)(\\p{Lu})", "$1_$2").toUpperCase(Locale.ROOT));
    }
}
