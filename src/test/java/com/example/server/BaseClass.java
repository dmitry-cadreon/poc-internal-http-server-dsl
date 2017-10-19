package com.example.server;

import com.example.server.model.Campaign;
import com.example.server.model.CampaignServiceRequest;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMessageVerifier
public class BaseClass {

    @Autowired
    CampaignController campaignController;

    @Before
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(campaignController);
    }

    public void triggerMessage(){
        Campaign campaign = Campaign.builder().build();
        campaignController.synchronizeCampaign(
            CampaignServiceRequest.builder().campaign(campaign).build());
    }

}
