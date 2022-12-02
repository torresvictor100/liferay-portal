/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {useForm} from 'react-hook-form';
import {useOutletContext} from 'react-router-dom';
import {KeyedMutator} from 'swr';

import Form from '../../../components/Form';
import Container from '../../../components/Layout/Container';
import Modal from '../../../components/Modal';
import {withVisibleContent} from '../../../hoc/withVisibleContent';
import {FormModalOptions} from '../../../hooks/useFormModal';
import i18n from '../../../i18n';
import yupSchema, {yupResolver} from '../../../schema/yup';
import {
	TestraySubTask,
	TestraySubTaskIssue,
	TestrayTask,
	testraySubTaskImpl,
} from '../../../services/rest';
import {CaseResultStatuses} from '../../../util/statuses';

type SubtaskForm = typeof yupSchema.subtask.__outputType;

type SubTaskCompleteModalProps = {
	modal: FormModalOptions;
	mutate?: KeyedMutator<any>;
	subtask: TestraySubTask;
};

type OutletContext = {
	mergedSubtaskNames: string;
	mutateSubtask: KeyedMutator<any>;
	mutateSubtaskIssues: KeyedMutator<TestraySubTask>;
	subtaskIssues: TestraySubTaskIssue[];
	testraySubtask: TestraySubTask;
	testrayTask: TestrayTask;
};

const SubtaskCompleteModal: React.FC<SubTaskCompleteModalProps> = ({
	modal: {observer, onClose, onError, onSave},
	subtask,
}) => {
	const {
		mutateSubtask,
		mutateSubtaskIssues,
		subtaskIssues = [],
		testraySubtask,
	} = useOutletContext<OutletContext>();

	const issues = subtaskIssues
		.map((subtaskIssue: TestraySubTaskIssue) => subtaskIssue?.issue?.name)
		.join(', ');

	const {
		formState: {errors},
		handleSubmit,
		register,
	} = useForm<SubtaskForm>({
		defaultValues: testraySubtask?.dueStatus
			? ({dueStatus: CaseResultStatuses.FAILED, issues} as any)
			: {dueStatus: CaseResultStatuses.FAILED},
		resolver: yupResolver(yupSchema.subtask),
	});

	const _onSubmit = ({dueStatus, issues = ''}: SubtaskForm) => {
		const _issues = issues
			.split(',')
			.map((name) => name.trim())
			.filter(Boolean);

		testraySubTaskImpl
			.complete(
				testraySubtask.id || subtask.id,
				dueStatus as string,
				_issues
			)
			.then(mutateSubtask)
			.then(mutateSubtaskIssues)
			.then(() => onSave())
			.catch(() => onError);
	};

	const inputProps = {
		errors,
		register,
	};

	return (
		<Modal
			last={
				<Form.Footer
					onClose={onClose}
					onSubmit={handleSubmit(_onSubmit)}
				/>
			}
			observer={observer}
			size="lg"
			title={i18n.sub('edit-x', 'status')}
			visible
		>
			<Container>
				<Form.Select
					className="container-fluid-max-md"
					defaultOption={false}
					label={i18n.translate('case-results-status')}
					name="dueStatus"
					options={[
						{label: 'Blocked', value: CaseResultStatuses.BLOCKED},
						{label: 'Failed', value: CaseResultStatuses.FAILED},
						{label: 'Passed', value: CaseResultStatuses.PASSED},
						{label: 'Test Fix', value: CaseResultStatuses.TEST_FIX},
					]}
					register={register}
				/>

				<Form.Input
					{...inputProps}
					className="container-fluid-max-md"
					label={i18n.translate('issues')}
					name="issues"
					register={register}
				/>

				<Form.Input
					{...inputProps}
					className="container-fluid-max-md"
					label={i18n.translate('comment')}
					name="comment"
					register={register}
					type="textarea"
				/>
			</Container>
		</Modal>
	);
};

export default withVisibleContent(SubtaskCompleteModal);
