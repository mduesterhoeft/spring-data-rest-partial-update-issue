package com.example;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import com.google.common.collect.ImmutableMap;

@Configuration
@EntityScan
public class JpaConfiguration extends JpaBaseConfiguration {

    @Value("${spring.jpa.show-sql}")
    private boolean showSql;

    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    protected Map<String, Object> getVendorProperties() {
        final ImmutableMap<String, Object> immutableMap = ImmutableMap.<String, Object>builder() //
                .put(PersistenceUnitProperties.TABLE_CREATION_SUFFIX, ";") //
                .put(PersistenceUnitProperties.WEAVING, "false") //
                .put(PersistenceUnitProperties.WEAVING_INTERNAL, "true") //
                .put(PersistenceUnitProperties.WEAVING_LAZY, "true") //
                .put(PersistenceUnitProperties.WEAVING_EAGER, "true") //
                .put(PersistenceUnitProperties.WEAVING_FETCHGROUPS, "true") //
                .put(PersistenceUnitProperties.WEAVING_CHANGE_TRACKING, "true") //
                .put(PersistenceUnitProperties.WEAVING_REST, "true") //
                .build();
        return newHashMap(immutableMap);
    }

    @Override
    protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
        vendorProperties.put(PersistenceUnitProperties.LOGGING_PARAMETERS, String.valueOf(showSql));
    }
}
