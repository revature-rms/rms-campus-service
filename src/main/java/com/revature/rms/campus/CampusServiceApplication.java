package com.revature.rms.campus;

import com.revature.rms.campus.entities.*;
import com.revature.rms.campus.repositories.CampusMongoRepository;
import com.revature.rms.campus.services.BuildingService;
import com.revature.rms.campus.services.CampusService;
import com.revature.rms.campus.services.RoomService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Campus API", version = "1.0", description = "Documentation Campus API v1.0")
)
public class CampusServiceApplication implements CommandLineRunner {

	@Autowired
	private CampusService campusService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private BuildingService buildingService;

	public static void main(String[] args) {
		SpringApplication.run(CampusServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerPersonApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.revature.rms.campus.controllers"))
					.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Campus API").description("Documentation Campus API v1.0").build());
	}

	@Override
	public void run(String... args) throws Exception {

//		Room room1 = new Room("107", 24, true, new ArrayList<RoomStatus>(), "1", new ArrayList<String>(), new ResourceMetadata());
//		Room room2 = new Room("300", 30, true, new ArrayList<RoomStatus>(), "2", new ArrayList<String>(), new ResourceMetadata());
//		Room room3 = new Room("320", 28, true, new ArrayList<RoomStatus>(), "3", new ArrayList<String>(), new ResourceMetadata());
//		Room room4 = new Room("328", 45, true, new ArrayList<RoomStatus>(), "4", new ArrayList<String>(), new ResourceMetadata());
//		Room room5 = new Room("200A", 26, true, new ArrayList<RoomStatus>(), "5", new ArrayList<String>(), new ResourceMetadata());
//		Room room6 = new Room("200B", 26, true, new ArrayList<RoomStatus>(), "6", new ArrayList<String>(), new ResourceMetadata());
//		Room room7 = new Room("1300", 30, true, new ArrayList<RoomStatus>(), "7", new ArrayList<String>(), new ResourceMetadata());
//		Room room8 = new Room("1304", 26, true, new ArrayList<RoomStatus>(), "8", new ArrayList<String>(), new ResourceMetadata());
//		Room room9 = new Room("1400", 26, true, new ArrayList<RoomStatus>(), "9", new ArrayList<String>(), new ResourceMetadata());
//		Room room10 = new Room("1402", 30, true, new ArrayList<RoomStatus>(), "10", new ArrayList<String>(), new ResourceMetadata());
//		Room room11 = new Room("2301A", 15, true, new ArrayList<RoomStatus>(), "11", new ArrayList<String>(), new ResourceMetadata());
//		Room room12 = new Room("2301B", 15, true, new ArrayList<RoomStatus>(), "12", new ArrayList<String>(), new ResourceMetadata());
//		Room room13 = new Room("2301C", 15, true, new ArrayList<RoomStatus>(), "13", new ArrayList<String>(), new ResourceMetadata());
//		Room room14 = new Room("2301D", 15, true, new ArrayList<RoomStatus>(), "14", new ArrayList<String>(), new ResourceMetadata());
//		Room room15 = new Room("2300", 30, true, new ArrayList<RoomStatus>(), "15", new ArrayList<String>(), new ResourceMetadata());
//		Room room16 = new Room("2303", 30, true, new ArrayList<RoomStatus>(), "16", new ArrayList<String>(), new ResourceMetadata());
//		Room room17 = new Room("2304", 30, true, new ArrayList<RoomStatus>(), "17", new ArrayList<String>(), new ResourceMetadata());
//
//		roomService.save(room1);
//		roomService.save(room2);
//		roomService.save(room3);
//		roomService.save(room4);
//		roomService.save(room5);
//		roomService.save(room6);
//		roomService.save(room7);
//		roomService.save(room8);
//		roomService.save(room9);
//		roomService.save(room10);
//		roomService.save(room11);
//		roomService.save(room12);
//		roomService.save(room13);
//		roomService.save(room14);
//		roomService.save(room15);
//		roomService.save(room16);
//		roomService.save(room17);
//
//		ArrayList<Room> building1Rooms = new ArrayList<>();
//		ArrayList<Room> building2Rooms = new ArrayList<>();
//
//		building1Rooms.add(room1);
//		building1Rooms.add(room2);
//		building1Rooms.add(room3);
//		building1Rooms.add(room4);
//		building1Rooms.add(room5);
//		building1Rooms.add(room6);
//
//		building2Rooms.add(room7);
//		building2Rooms.add(room8);
//		building2Rooms.add(room9);
//		building2Rooms.add(room10);
//		building2Rooms.add(room11);
//		building2Rooms.add(room12);
//		building2Rooms.add(room13);
//		building2Rooms.add(room14);
//		building2Rooms.add(room15);
//		building2Rooms.add(room16);
//		building2Rooms.add(room17);
//
//		Building building1 = new Building("NEC", "NEC", new Address(), 5, new ArrayList<Amenity>(), building1Rooms, new ResourceMetadata());
//		Building building2 = new Building("BSN", "BSN", new Address(), 4, new ArrayList<Amenity>(), building2Rooms, new ResourceMetadata());
//
//		buildingService.save(building1);
//		buildingService.save(building2);
//
//		ArrayList<Building> campus1Buildings = new ArrayList<>();
//		campus1Buildings.add(building1);
//		campus1Buildings.add(building2);
//
//		Campus campus1 = new Campus("University of South Florida", "USF", new Address(), 1, 2, 3, campus1Buildings, new ArrayList<Integer>(), new ResourceMetadata());
//		campusService.save(campus1);

		//test

		ResourceMetadata meta = new ResourceMetadata(1,"3/20/20", 1, "3/20/20",1);
		ArrayList<RoomStatus> roomStatus1 = new ArrayList<>();
		roomStatus1.add(new RoomStatus(1, true, true, "1/1/19", 1, "Good", true));
		ArrayList<RoomStatus> roomStatus2 = new ArrayList<>();
		roomStatus2.add(new RoomStatus(2, true, true, "1/1/19", 2, "Good", true));
		ArrayList<RoomStatus> roomStatus3 = new ArrayList<>();
		roomStatus3.add(new RoomStatus(3, true, true, "1/1/19", 3, "Good", true));
		ArrayList<RoomStatus> roomStatus4 = new ArrayList<>();
		roomStatus4.add(new RoomStatus(4, true, true, "1/1/19", 4, "Good", true));
		ArrayList<RoomStatus> roomStatus5 = new ArrayList<>();
		roomStatus5.add(new RoomStatus(5, true, true, "1/1/19", 5, "Good", true));
		ArrayList<RoomStatus> roomStatus6 = new ArrayList<>();
		roomStatus6.add(new RoomStatus(6, true, true, "1/1/19", 6, "Good", true));
		ArrayList<RoomStatus> roomStatus7 = new ArrayList<>();
		roomStatus7.add(new RoomStatus(7, true, true, "1/1/19", 7, "Good", true));
		ArrayList<RoomStatus> roomStatus8 = new ArrayList<>();
		roomStatus8.add(new RoomStatus(8, true, true, "1/1/19", 8, "Good", true));
		ArrayList<RoomStatus> roomStatus9 = new ArrayList<>();
		roomStatus9.add(new RoomStatus(9, true, true, "1/1/19", 9, "Good", true));
		ArrayList<RoomStatus> roomStatus10 = new ArrayList<>();
		roomStatus10.add(new RoomStatus(10, true, true, "1/1/19", 10, "Good", true));
		ArrayList<RoomStatus> roomStatus11 = new ArrayList<>();
		roomStatus11.add(new RoomStatus(11, true, true, "1/1/19", 11, "Good", true));
		ArrayList<RoomStatus> roomStatus12 = new ArrayList<>();
		roomStatus12.add(new RoomStatus(12, true, true, "1/1/19", 12, "Good", true));
		ArrayList<RoomStatus> roomStatus13 = new ArrayList<>();
		roomStatus13.add(new RoomStatus(13, true, true, "1/1/19", 13, "Good", true));
		ArrayList<RoomStatus> roomStatus14 = new ArrayList<>();
		roomStatus14.add(new RoomStatus(14, true, true, "1/1/19", 14, "Good", true));
		ArrayList<RoomStatus> roomStatus15 = new ArrayList<>();
		roomStatus15.add(new RoomStatus(15, true, true, "1/1/19", 15, "Good", true));
		ArrayList<RoomStatus> roomStatus16 = new ArrayList<>();
		roomStatus16.add(new RoomStatus(16, true, true, "1/1/19", 16, "Good", true));
		ArrayList<RoomStatus> roomStatus17 = new ArrayList<>();
		roomStatus17.add(new RoomStatus(17, true, true, "1/1/19", 17, "Good", true));
		ArrayList<String> work1 = new ArrayList<>();
		work1.add("1");
		ArrayList<String> work2 = new ArrayList<>();
		work2.add("2");
		ArrayList<String> work3 = new ArrayList<>();
		work3.add("3");
		ArrayList<String> work4 = new ArrayList<>();
		work4.add("4");
		ArrayList<String> work5 = new ArrayList<>();
		work5.add("5");
		ArrayList<String> work6 = new ArrayList<>();
		work6.add("6");
		ArrayList<String> work7 = new ArrayList<>();
		work7.add("7");
		ArrayList<String> work8 = new ArrayList<>();
		work8.add("8");
		ArrayList<String> work9 = new ArrayList<>();
		work9.add("9");
		ArrayList<String> work10 = new ArrayList<>();
		work10.add("10");
		ArrayList<String> work11 = new ArrayList<>();
		work11.add("11");
		ArrayList<String> work12 = new ArrayList<>();
		work12.add("12");
		ArrayList<String> work13 = new ArrayList<>();
		work13.add("13");
		ArrayList<String> work14 = new ArrayList<>();
		work14.add("14");
		ArrayList<String> work15 = new ArrayList<>();
		work15.add("15");
		ArrayList<String> work16 = new ArrayList<>();
		work16.add("16");
		ArrayList<String> work17 = new ArrayList<>();
		work17.add("17");
		Room room1 = new Room("107", 24, true,  roomStatus1, "1", work1, meta);
		Room room2 = new Room("300", 30, true,  roomStatus2, "2", work2, meta);
		Room room3 = new Room("320", 28, true,  roomStatus3, "3", work3, meta);
		Room room4 = new Room("328", 45, true,  roomStatus4, "4", work4, meta);
		Room room5 = new Room("200A", 26, true,  roomStatus5, "5", work5, meta);
		Room room6 = new Room("200B", 26, true, roomStatus6, "6", work6, meta);
		Room room7 = new Room("1300", 30, true,  roomStatus7, "7", work7, meta);
		Room room8 = new Room("1304", 26, true,  roomStatus8, "8", work8, meta);
		Room room9 = new Room("1400", 26, true,  roomStatus9, "9", work9, meta);
		Room room10 = new Room("1402", 30, true,  roomStatus10, "10", work10, meta);
		Room room11 = new Room("2301A", 15, true,  roomStatus11, "11", work11, meta);
		Room room12 = new Room("2301B", 15, true,  roomStatus12, "12", work12, meta);
		Room room13 = new Room("2301C", 15, true, roomStatus13, "13", work13, meta);
		Room room14 = new Room("2301D", 15, true, roomStatus14, "14", work14, meta);
		Room room15 = new Room("2300", 30, true, roomStatus15, "15", work15, meta);
		Room room16 = new Room("2303", 30, true, roomStatus16, "16", work16, meta);
		Room room17 = new Room("2304", 30, true, roomStatus17, "17", work17, meta);
		roomService.save(room1);
		roomService.save(room2);
		roomService.save(room3);
		roomService.save(room4);
		roomService.save(room5);
		roomService.save(room6);
		roomService.save(room7);
		roomService.save(room8);
		roomService.save(room9);
		roomService.save(room10);
		roomService.save(room11);
		roomService.save(room12);
		roomService.save(room13);
		roomService.save(room14);
		roomService.save(room15);
		roomService.save(room16);
		roomService.save(room17);
		ArrayList<Room> building1Rooms = new ArrayList<>();
		ArrayList<Room> building2Rooms = new ArrayList<>();
		building1Rooms.add(room1);
		building1Rooms.add(room2);
		building1Rooms.add(room3);
		building1Rooms.add(room4);
		building1Rooms.add(room5);
		building1Rooms.add(room6);
		building2Rooms.add(room7);
		building2Rooms.add(room8);
		building2Rooms.add(room9);
		building2Rooms.add(room10);
		building2Rooms.add(room11);
		building2Rooms.add(room12);
		building2Rooms.add(room13);
		building2Rooms.add(room14);
		building2Rooms.add(room15);
		building2Rooms.add(room16);
		building2Rooms.add(room17);
		Address address = new Address("4202 E Fowler Ave", "Tampa", "FL", "33620", "US");
		ArrayList<Amenity> amenities = new ArrayList<>();
		amenities.add(new Amenity(AmenityType.COFFEE, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.TEA, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.WATER, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.UTENSILS, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.COFFEE_FILTERS, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.COFFEE_CREAMER, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.PAPER_TOWELS, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.CLEANING_WIPES, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.WHITEBOARD_ERASERS, AmenityStatus.OK));
		amenities.add(new Amenity(AmenityType.WHITEBOARD_MARKERS, AmenityStatus.OK));
		Building building1 = new Building("NEC", "NEC", address, 5, amenities, building1Rooms, meta);
		Building building2 = new Building("MUMA School of Business", "BSN", address, 4, amenities, building2Rooms, meta);
		buildingService.save(building1);
		buildingService.save(building2);
		ArrayList<Building> campus1Buildings = new ArrayList<>();
		campus1Buildings.add(building1);
		campus1Buildings.add(building2);
		ArrayList<Integer> corporate = new ArrayList<>();
		corporate.add(1);
		corporate.add(2);
		corporate.add(3);
		corporate.add(4);
		corporate.add(5);
		corporate.add(6);
		corporate.add(7);
		corporate.add(8);
		corporate.add(9);
		corporate.add(10);
		corporate.add(11);
		corporate.add(12);
		corporate.add(13);
		corporate.add(14);
		corporate.add(15);
		corporate.add(16);
		corporate.add(17);
		Campus campus1 = new Campus("1","University of South Florida", "USF", address, 1, 2, 3, campus1Buildings, corporate, meta);
		campusService.save(campus1);
	}
}
