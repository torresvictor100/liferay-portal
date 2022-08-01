package com.liferay.message.boards.moderation.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;
@ExtendedObjectClassDefinition(
	category = "message-boards",
	scope = ExtendedObjectClassDefinition.Scope.GROUP

)
@Meta.OCD(
	id = "com.liferay.message.boards.moderation.configuration.MBTherdDeleteForMonthConfiguration",
	localization = "content/Language",
	name = "MBTherd Delete For Month Configuration"
)
public interface MBTherdDeleteForMonthConfiguration {


	@Meta.AD(
		deflt = "false", description = "enable message boards deleta automatico",
		name = "enable message boards deleta automatico", required = false
	)
	public boolean enableMessageBoardsDeletaAutomatico();

	@Meta.AD(
		deflt = "5", description = "message boards deleta periodo sem resposta",
		name = "message boards deleta periodo sem resposta", required = false
	)
	public int messageBoardsDeletaPeriodoSemResposta();


}
