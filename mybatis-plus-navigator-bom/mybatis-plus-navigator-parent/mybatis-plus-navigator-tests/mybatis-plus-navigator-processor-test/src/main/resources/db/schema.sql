DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee`
(
    `id`          BIGINT PRIMARY KEY COMMENT 'ID',
    `organization_id`   BIGINT       NOT NULL COMMENT 'Organization ID',
    `organization_name` VARCHAR(128) NOT NULL COMMENT 'Organization name',
    `employee_no` VARCHAR(32) NOT NULL COMMENT 'Employee no.',
    `sorting`     INT         NOT NULL COMMENT 'Sorting order',
    `status`      INT         NOT NULL COMMENT 'Status',
    `remark`      VARCHAR(128) NULL DEFAULT NULL COMMENT 'Remark',
    `createdBy`   BIGINT      NOT NULL COMMENT 'Created by',
    `updatedBy`   BIGINT      NOT NULL COMMENT 'Updated by',
    `createdAt`   TIMESTAMP   NOT NULL COMMENT 'Created at',
    `updatedAt`   TIMESTAMP   NOT NULL COMMENT 'Updated at',
    `deleted`     TINYINT     NOT NULL COMMENT 'Deleted'
);

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization`
(
    `id`                BIGINT PRIMARY KEY COMMENT 'ID',
    `organization_no`   BIGINT       NOT NULL COMMENT 'Organization No',
    `organization_name` VARCHAR(128) NOT NULL COMMENT 'Organization name',
    `sorting`           INT          NOT NULL COMMENT 'Sorting order',
    `status`            INT          NOT NULL COMMENT 'Status',
    `remark`            VARCHAR(128) NULL DEFAULT NULL COMMENT 'Remark',
    `createdBy`         BIGINT       NOT NULL COMMENT 'Created by',
    `updatedBy`         BIGINT       NOT NULL COMMENT 'Updated by',
    `createdAt`         TIMESTAMP    NOT NULL COMMENT 'Created at',
    `updatedAt`         TIMESTAMP    NOT NULL COMMENT 'Updated at',
    `deleted`           TINYINT      NOT NULL COMMENT 'Deleted'
);
