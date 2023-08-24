CREATE TABLE `person` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `cpf` varchar(14) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `address` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `person_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK81ihijcn1kdfwffke0c0sjqeb` (`person_id`),
    CONSTRAINT `FK81ihijcn1kdfwffke0c0sjqeb` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
)