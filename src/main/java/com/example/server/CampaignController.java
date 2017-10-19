package com.example.server;

import com.example.server.model.Campaign;
import com.example.server.model.CampaignServiceRequest;
import com.example.server.model.CampaignSynchronizationResult;
import com.example.server.model.CampaignSynchronizationStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
public class CampaignController {

    @Autowired
    private ObjectMapper mapper;

	@RequestMapping(value = "/campaign", method = POST)
	public CampaignSynchronizationResult synchronizeCampaign(@RequestBody CampaignServiceRequest request) {
	    Campaign campaign = request.getCampaign();
		if (!isValid(campaign)) {
            CampaignSynchronizationResult r =  CampaignSynchronizationResult.builder()
                .campaign(campaign)
                .status(CampaignSynchronizationStatus.FAILED)
                .message("validation.failed")
                .build();
            return r;
		}

		return CampaignSynchronizationResult.builder()
            .campaign(campaign)
            .status(CampaignSynchronizationStatus.SUCCESS)
            .build();
	}

	private boolean isValid(Campaign campaign) {
        try {
            log.info("[x]Campaign: {}", mapper.writeValueAsString(campaign));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return campaign != null && campaign.getDate() != null;
	}

}
