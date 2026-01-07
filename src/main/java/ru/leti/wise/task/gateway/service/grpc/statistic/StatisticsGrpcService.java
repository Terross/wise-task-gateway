package ru.leti.wise.task.gateway.service.grpc.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.leti.wise.task.event.Statistic;


@Component
@RequiredArgsConstructor
public class StatisticsGrpcService {
    private final StatisticsStubHolder statisticsStubHolder;

    public Statistic.StatisticResponse getStatistic(Statistic.StatisticRequest request){

        return statisticsStubHolder.get().getStatistic(
                request
        );
    }
}
