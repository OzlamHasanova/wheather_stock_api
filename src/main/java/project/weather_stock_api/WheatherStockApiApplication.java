package project.weather_stock_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import project.weather_stock_api.service.impl.DatabaseCleanupServiceImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static project.weather_stock_api.service.impl.DatabaseCleanupServiceImpl.cleanupDatabase;

@SpringBootApplication
@EnableFeignClients
public class WheatherStockApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheatherStockApiApplication.class, args);

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(DatabaseCleanupServiceImpl::cleanupDatabase, 0, 2, TimeUnit.HOURS);
	}

}


