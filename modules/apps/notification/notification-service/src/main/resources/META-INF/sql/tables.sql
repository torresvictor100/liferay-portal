create table NotificationQueueEntry (
	mvccVersion LONG default 0 not null,
	notificationQueueEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	notificationTemplateId LONG,
	bcc VARCHAR(75) null,
	body VARCHAR(75) null,
	cc VARCHAR(75) null,
	classNameId LONG,
	classPK LONG,
	from_ VARCHAR(75) null,
	fromName VARCHAR(75) null,
	priority DOUBLE,
	sent BOOLEAN,
	sentDate DATE null,
	subject VARCHAR(75) null,
	to_ VARCHAR(75) null,
	toName VARCHAR(75) null
);

create table NotificationTemplate (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	notificationTemplateId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bcc VARCHAR(75) null,
	body TEXT null,
	cc VARCHAR(75) null,
	description VARCHAR(75) null,
	enabled BOOLEAN,
	from_ VARCHAR(75) null,
	fromName STRING null,
	name STRING null,
	subject STRING null,
	to_ STRING null
);