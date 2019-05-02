-- // create-credential-table
-- Migration SQL that makes the change goes here.
CREATE SEQUENCE IF NOT EXISTS credential_id_seq;
CREATE TABLE "public"."credential" (
    "id" int8 NOT NULL DEFAULT nextval('credential_id_seq'::regclass),
    "account" varchar(255),
    "description" text,
    "name" varchar(255) NOT NULL,
    "owner" varchar(255),
    "publicinaccount" bool NOT NULL DEFAULT false,
    "publickey" text,
    "archived" bool DEFAULT false,
    "loginusername" text,
    "attributes" text,
    "cloudplatform" varchar(255) NOT NULL,
    "topology_id" int8,
    "govcloud" bool DEFAULT false,
    PRIMARY KEY ("id")
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS "public"."credential";
DROP SEQUENCE IF EXISTS credential_id_seq;
