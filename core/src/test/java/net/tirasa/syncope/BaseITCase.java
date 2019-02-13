package net.tirasa.syncope;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.syncope.client.lib.SyncopeClient;
import org.apache.syncope.client.lib.SyncopeClientFactoryBean;
import org.apache.syncope.common.rest.api.service.SyncopeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseITCase {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseITCase.class);

    protected static final String ADMIN_UNAME = "admin";

    protected static final String ADMIN_PWD = "password";

    protected static final String ADDRESS = "http://localhost:9080/syncope/rest";

    protected static SyncopeClientFactoryBean clientFactory;

    protected static SyncopeClient adminClient;

    protected static SyncopeService syncopeService;

    @BeforeAll
    public static void restSetup() {
        clientFactory = new SyncopeClientFactoryBean().setAddress(ADDRESS);

        LOG.info("Performing IT with content type {}", clientFactory.getContentType().getMediaType());

        adminClient = clientFactory.create(ADMIN_UNAME, ADMIN_PWD);

        syncopeService = adminClient.getService(SyncopeService.class);
    }

    @Test
    public void base() {
        assertFalse(syncopeService.platform().getProvisioningInfo().getUserProvisioningManager().contains("Camel"));
    }
}
