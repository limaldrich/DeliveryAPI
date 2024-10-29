package com.example.deliveryapi.services;

import com.example.deliveryapi.exception.BadRequestException;
import com.example.deliveryapi.mapper.ThresholdJsonFileMapper;
import com.example.deliveryapi.requests.ItemRequest;
import com.example.deliveryapi.model.Thresholds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    private ThresholdJsonFileMapper thresholdJsonFileMapper;

    public Double calculateDeliveryCost(ItemRequest itemRequest) {
        Thresholds thresholds = thresholdJsonFileMapper.getThresholds();

        double maxWeight = thresholds.getWeight().getMaxWeight();
        double heavyParcel = thresholds.getWeight().getHeavyParcelThreshold();
        double smallParcelVolume = thresholds.getVolume().getSmallParcelThreshold();
        double mediumParcelVolume = thresholds.getVolume().getMediumParcelThreshold();

        double volume = itemRequest.getLength() * itemRequest.getWidth() * itemRequest.getHeight();

        if (itemRequest.getWeight() > maxWeight) {
            throw new BadRequestException("Weight is greater than the maximum weight of " + maxWeight);
        }
        if (itemRequest.getWeight() > heavyParcel) {
            return 20 * itemRequest.getWeight();
        } else if (volume < smallParcelVolume) {
            return 0.03 * volume;
        } else if (volume < mediumParcelVolume) {
            return 0.04 * volume;
        } else {
            return 0.05 * volume;
        }

    }


}
