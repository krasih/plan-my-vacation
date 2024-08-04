package com.example.planmyvacation.scheduled;

import com.example.planmyvacation.model.dto.CareerDTO;
import com.example.planmyvacation.service.CareerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CareersWatcher {

    private static final Logger log = LoggerFactory.getLogger(CareersWatcher.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final CareerService careerService;

    public CareersWatcher(CareerService careerService) {
        this.careerService = careerService;
    }


//    CRON job is running every minute of the hour at 00 seconds
    @Scheduled(cron = "0 * * * * ?")
    public void updateCareersPublished() {

        List<CareerDTO> allCareers = careerService.findAll();

        for (CareerDTO careerDTO : allCareers) {

            String published = switch (careerDTO.getPublished()) {
                case "now" :

                    yield "1 minute ago";
                case "1 minute ago" : yield "2 minutes ago";
                case "2 minutes ago" : yield "3 minutes ago";
                case "3 minutes ago" : yield "4 minutes ago";
                case "4 minutes ago" : yield "5 minutes ago";
                default : yield careerDTO.getPublished();
            };

            if (!careerDTO.getPublished().equals(published)) {

                String msg = "Career (id=" +careerDTO.getId()+ ") has been updated!";

                log.info("{} >>> {}", dateFormat.format(new Date()), msg);

                careerDTO.setPublished(published);
                careerService.update(careerDTO.getId(), careerDTO);
            }
        }
    }

}
