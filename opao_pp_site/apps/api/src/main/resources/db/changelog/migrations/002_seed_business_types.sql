--liquibase formatted sql

--changeset opao-team:seed-business-types-from-csv-v1
--loadData tableName="business_type" file="static-data/business_types.csv" separator=","
