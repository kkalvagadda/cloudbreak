-- // create-environment-table
-- Migration SQL that makes the change goes here.

CREATE SEQUENCE IF NOT EXISTS environment_id_seq;
CREATE TABLE "public"."environment" (
    "id" int8 NOT NULL DEFAULT nextval('environment_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "description" text,
    "cloudplatform" varchar(255) NOT NULL,
    "credential_id" int8 NOT NULL,
    "regions" text NOT NULL,
    "location" varchar(255),
    "longitude" float8,
    "latitude" float8,
    "sdxresources_id" int8,
    "locationdisplayname" varchar(255),
    "archived" bool DEFAULT false,
    "deletiontimestamp" int8 DEFAULT '-1'::integer,
    CONSTRAINT "fk_environment_credential_id" FOREIGN KEY ("credential_id") REFERENCES "public"."credential"("id"),
    PRIMARY KEY ("id")
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS environment;
DROP SEQUENCE IF EXISTS environment_id_seq;