package com.liferay.message.boards.moderation.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;
@ExtendedObjectClassDefinition(
	category = "message-boards",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
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
		deflt = "1", description = "message boards deleta periodo sem resposta",
		min = "3", name = "message boards deleta periodo sem resposta", required = false
	)
	public int messageBoardsDeletaPeriodoSemResposta();

	@Meta.AD(
		deflt = "1", description = "ids dos grups",
		min = "1", name = "message boards deleta id do grups", required = false
	)
	public String[] grupsIds();

}
