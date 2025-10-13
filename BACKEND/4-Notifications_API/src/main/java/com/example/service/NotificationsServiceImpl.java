package com.example.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.WatiParameters;
import com.example.dto.WatiRequest;
import com.example.dto.WatiResponse;
import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.repo.OrderRepo;

@Service
public class NotificationsServiceImpl implements NotificationService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private EmailService emailService;

	@Value("${wati.token}")
	private String watiToken;

	@Value("${wati.template.name}")
	private String templateName;

	@Value("${wati.endpoint.url}")
	private String watiEndPointUrl;

	@Override
	@Scheduled(cron = "0 7 * * * *")
	public Integer sendDeliveryNotifications() {

		List<Order> orders = orderRepo.findByDeliveryDate(LocalDate.now());

		for (Order order : orders) {

			Customer customer = order.getCustomer();

			sendEmailNotification(customer.getEmail(), order.getOrderTrackingNum());
			sendWatiNotification(customer, order.getOrderTrackingNum());

		}
		return orders.size();
	}

	@Override
	public Integer sendNotificationToPendingOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean sendEmailNotification(String to, String orderTrackingNumber) {

		String subject = "Your Order Out For Delivery.";
		String body = "Your Order " + orderTrackingNumber + "will be Delivered Today.";
		return emailService.sendEmail(to, subject, body);
	}

	public WatiResponse sendWatiNotification(Customer customer, String orderTrackingNumber) {

		RestTemplate rt = new RestTemplate();

		String apiUrl = watiEndPointUrl + "?whatsAppNumber=91" + customer.getPhoneNo();

		WatiParameters nameParam = new WatiParameters();
		nameParam.setName("name");
		nameParam.setValue(customer.getCustomerName());

		WatiParameters trackingParam = new WatiParameters();
		trackingParam.setName("order_tracking_number");
		trackingParam.setValue(orderTrackingNumber);

		WatiRequest request = new WatiRequest();
		request.setTemplate_name(templateName);
		request.setBroadcast_name(templateName + "BD");
		request.setParameters(Arrays.asList(nameParam, trackingParam));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "watiToken");

		HttpEntity<WatiRequest> reqEntity = new HttpEntity<WatiRequest>(request, headers);

		ResponseEntity<WatiResponse> postForEntity = rt.postForEntity(apiUrl, reqEntity, WatiResponse.class);

		return postForEntity.getBody();
	}

}
