CREATE TABLE public.user
(
    id BIGSERIAL,
    open_id VARCHAR(255),
    union_id VARCHAR(255),
    nickname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    signature character varying(255) COLLATE pg_catalog."default" NOT NULL,
    gender BOOLEAN,
    phone bigint,
    email character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",

    create_at bigint,
    update_at bigint,

    CONSTRAINT user_pkey PRIMARY KEY (id)
)