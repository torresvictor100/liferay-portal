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

import ClaySticker from '@clayui/sticker';
import {ClayTooltipProvider} from '@clayui/tooltip';
import classNames from 'classnames';
import React from 'react';

import {ALIGN_POSITIONS} from '../Tooltip';

type AvatarProps = {
	className?: string;
	displayName?: boolean;
	displayTooltip?: boolean;
	expanded?: boolean;
	name?: string;
	size?: 'lg' | 'sm' | 'xl';
	tooltipPosition?: ALIGN_POSITIONS;
	url?: string;
};

type AvatarGroupProps = {
	assignedUsers: AvatarProps[];
	className?: string;
	groupSize: number;
};

function getInitials(name: string): string {
	return name
		.split(' ')
		.map((value) => value.charAt(0))
		.join('')
		.toLocaleUpperCase();
}

function getRandomColor(name: string) {
	const backgroundAccentColorsRegex = {
		'bg-accent-1': /^[A-Fa-f]/g,
		'bg-accent-2': /^[G-Lg-l]/g,
		'bg-accent-3': /^[M-Tm-t]/g,
		'bg-accent-4': /^[U-Zu-z]/g,
	};

	for (const bgAccent in backgroundAccentColorsRegex) {
		const value = (backgroundAccentColorsRegex as any)[bgAccent];

		if (new RegExp(value).test(name)) {
			return bgAccent;
		}
	}
}

const Avatar: React.FC<AvatarProps> & {Group: React.FC<AvatarGroupProps>} = ({
	className,
	displayName = false,
	displayTooltip = true,
	expanded,
	name = '',
	size = 'lg',
	tooltipPosition = 'bottom',
	url,
}) => {
	const TooltipWrapper = displayTooltip
		? ClayTooltipProvider
		: React.Fragment;

	return (
		<div className="align-items-center d-flex">
			<TooltipWrapper>
				<ClaySticker
					className={classNames(
						className,
						'text-brand-secondary-lighten-6',
						getRandomColor(getInitials(name))
					)}
					shape="circle"
					size={size}
					{...(displayTooltip && {
						'data-tooltip-align': tooltipPosition,
						'title': name,
					})}
				>
					{url ? (
						<ClaySticker.Image alt={name} src={url} />
					) : (
						getInitials(name)
					)}
				</ClaySticker>
			</TooltipWrapper>

			{displayName && (
				<span
					className={classNames(
						className,
						'ml-3 testray-avatar-dropdown-text',
						{
							'testray-avatar-dropdown-text-expanded': expanded,
						}
					)}
				>
					{name}
				</span>
			)}
		</div>
	);
};

Avatar.Group = ({assignedUsers, groupSize}) => {
	const totalAssignedUsers = assignedUsers.length;

	return (
		<div className="d-flex">
			<div className="align-items-center avatar-group d-flex justify-content-end p-0">
				{assignedUsers
					.filter((_, index) => index < groupSize)
					.map((user, index) => (
						<div className="avatar-group-item" key={index}>
							<Avatar
								className="avatar avatar-skeleton-loader shadow-lg"
								name={user.name}
								url={user.url}
							/>
						</div>
					))}
			</div>

			<ClayTooltipProvider>
				<div
					className="align-items-center avatar-plus d-flex justify-content-center p-0 pl-4 pr-2 text-nowrap"
					data-tooltip-align="bottom"
					title={assignedUsers
						.filter((_, index) => index >= groupSize)
						.map(({name}) => name)
						.toString()}
				>
					{totalAssignedUsers > groupSize &&
						`+ ${totalAssignedUsers - groupSize}`}
				</div>
			</ClayTooltipProvider>
		</div>
	);
};

export default Avatar;
