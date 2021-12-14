CREATE TABLE public.platform_configuration (
    id bigint NOT NULL,
    configuration_variable character varying(255) NOT NULL,
    date_value timestamp without time zone,
    variable_type character varying(255) NOT NULL
);



INSERT INTO public.platform_configuration VALUES
            (1, 'REGION_CASE_HISTORY_NEWS_LAST_UPDATE', null,'Timestamp'),
            (2, 'VACCINATION_INFO_LAST_UPDATE', null,'Timestamp'),
            (3, 'COVID19_NEWS_LAST_UPDATE', null,'Timestamp'),
            (4, 'CONFIRMED_CASES_LAST_UPDATE', null,'Timestamp'),
            (5, 'CONFIRMED_DEATHS_LAST_UPDATE', null,'Timestamp'),
            (6, 'GENDER_AGE_LAST_UPDATE', null,'Timestamp'),
            (7, 'INTENSIVE_CARE_LAST_UPDATE', null,'Timestamp'),
            (8, 'TOTAL_TESTS_LAST_UPDATE', null,'Timestamp');