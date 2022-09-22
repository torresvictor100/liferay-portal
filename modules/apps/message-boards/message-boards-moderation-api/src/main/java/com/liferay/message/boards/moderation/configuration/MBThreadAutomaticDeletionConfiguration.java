package com.liferay.message.boards.moderation.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author João Victor
 */
@ExtendedObjectClassDefinition(
	category = "message-boards",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.message.boards.moderation.configuration.MBThreadAutomaticDeletionConfiguration",
	localization = "content/Language",
	name = "MBThread Configuration Automatic Deletion"
)
public interface MBThreadAutomaticDeletionConfiguration {

	@Meta.AD(
		deflt = "false", description = "You want enable that olds posts no answer be delete",
		name = "Enable delete old message no answer", required = false
	)
	public boolean enableDeleteOldMessageNoAnswer();

	@Meta.AD(
		deflt = "false", description = "you enable delete all old post",
		name = "Enable delete all old post", required = false
	)
	public boolean enableDeleteAllOldPost();


	@Meta.AD(
		deflt = "6", description = "How many month enable start to the delete",
		min = "6",max = "240" , name = "Month Start To The Delete", required = false
	)
	public int monthStartToTheDelete();

	@Meta.AD(
		deflt = "", description = "Witer the I accepted Start To Delete Old Post 'accepted'",
		 name = "Confimation start to the delete", required = false
	)
	public String confimationStartToTheDelete();

}
