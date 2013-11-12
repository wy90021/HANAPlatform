create schema cmu;
create table cmu.cmu_sensor(deviceid varchar(20), timestamp integer, sensortype varchar(20), value double);


CREATE TABLE "CMU"."COURSE_SENSOR_CATEGORY" ("SENSOR_CATEGORY_ID" INTEGER  NOT NULL ,
	 "SENSOR_CATEGORY_NAME" VARCHAR(256),
	 "PURPOSE" VARCHAR(256),
	 UNIQUE ("SENSOR_CATEGORY_NAME"),
	 PRIMARY KEY ("SENSOR_CATEGORY_ID"));
	 
CREATE  TABLE "CMU"."COURSE_SENSOR_TYPE" ("SENSOR_TYPE_ID" INTEGER NOT NULL ,
	 "SENSOR_TYPE_NAME" VARCHAR(256),
	 "MANUFACTURER" VARCHAR(256),
	 "VERSION" VARCHAR(256),
	 "MAX_VALUE" DOUBLE ,
	 "MIN_VALUE" DOUBLE ,
	 "UNIT" VARCHAR(256),
	 "INTERPRETER" VARCHAR(256),
	 "USER_DEFINED_FIELDS" VARCHAR(256),
	 "SENSOR_CATEGORY_ID" INTEGER ,
	 UNIQUE ("SENSOR_TYPE_NAME"),
	 PRIMARY KEY ("SENSOR_TYPE_ID"));
	 
CREATE TABLE "CMU"."COURSE_DEVICE_TYPE" ("DEVICE_TYPE_ID" INTEGER ,
	 "DEVICE_TYPE_NAME" VARCHAR(256),
	 "MANUFACTURER" VARCHAR(256),
	 "VERSION" VARCHAR(256),
	 "USER_DEFINED_FIELDS" VARCHAR(256),
	 UNIQUE ("DEVICE_TYPE_NAME"),
	 PRIMARY KEY ("DEVICE_TYPE_ID"));
	 
CREATE TABLE "CMU"."COURSE_DEVICE" ("DEVICE_ID" INTEGER ,
	 "DEVICE_TYPE_ID" INTEGER ,
	 "URI" VARCHAR(256),
	 "REGISTRATION_TIMESTAMP" TIMESTAMP ,
	 "USER_DEFINED_FIELDS" VARCHAR(256),
	 UNIQUE ("URI"),
	 PRIMARY KEY ("DEVICE_ID"));
	 
CREATE  TABLE "CMU"."COURSE_SENSOR" ("SENSOR_ID" INTEGER  NOT NULL ,
	 "SENSOR_TYPE_ID" INTEGER ,
	 "DEVICE_ID" INTEGER ,
	 "SENSOR_NAME" VARCHAR(256),
	 "USER_DEFINED_FIELDS" VARCHAR(256),
	 UNIQUE ("SENSOR_NAME"),
	 PRIMARY KEY ("SENSOR_ID"));
	 
create sequence "CMU"."COURSE_SENSOR_CATEGORY_ID_SEQ" increment by 1 start with 1 minvalue 1 maxvalue 1000 no cycle

create sequence "CMU"."COURSE_SENSOR_TYPE_ID_SEQ" increment by 1 start with 1 minvalue 1 maxvalue 1000 no cycle 

create sequence "CMU"."COURSE_SENSOR_ID_SEQ" increment by 1 start with 1 minvalue 1 maxvalue 1000 no cycle 

create sequence "CMU"."COURSE_DEVICE_TYPE_ID_SEQ" increment by 1 start with 1 minvalue 1 maxvalue 1000 no cycle 

create sequence "CMU"."COURSE_DEVICE_ID_SEQ" increment by 1 start with 1 minvalue 1 maxvalue 1000 no cycle 







 