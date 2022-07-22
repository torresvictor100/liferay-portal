package com.liferay.message.boards.moderation.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "message-boards",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.message.boards.moderation.configuration.MBTherdConfiguration",
	localization = "content/Language",
	name = "MBTherd-Configuration-moderation-thred-delete"
)
public interface MBTherdConfiguration {


	@Meta.AD(
		deflt = "false", description = "enable-message-boards-deleta-automatico",
		name = "enable-message-boards-deleta-automatico", required = false
	)
	public boolean enableMessageBoardsDeletaAutomatico();

	@Meta.AD(
		deflt = "false", description = "repeticoes-semanal",
		name = "repeticoes-semanal", required = false
	)
	public boolean repeticoesSemanal();

	@Meta.AD(
		deflt = "false", description = "repeticoes-mensais",
		name = "repeticoes-mensais", required = false
	)
	public boolean repeticoeMensais();

	@Meta.AD(
		deflt = "1", description = "message-boards-deleta-periodo-sem-resposta",
		min = "1", name = "message-boards-deleta-periodo-sem-resposta", required = false
	)
	public int messageBoardsDeletaPeriodoSemResposta();
}
