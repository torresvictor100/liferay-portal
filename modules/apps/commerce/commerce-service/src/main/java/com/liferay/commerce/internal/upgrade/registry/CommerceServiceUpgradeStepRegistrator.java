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

package com.liferay.commerce.internal.upgrade.registry;

import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountOrganizationRelLocalService;
import com.liferay.commerce.internal.upgrade.v1_2_0.CommerceSubscriptionUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v2_0_0.CommercePaymentMethodUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v2_1_0.CPDAvailabilityEstimateUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v2_1_0.CommerceSubscriptionEntryUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v4_1_0.CommerceAddressUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v4_3_0.CommerceOrderDateUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v4_5_1.CommerceShippingMethodUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v4_8_1.CommerceOrderStatusesUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v5_0_1.CommercePermissionUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v7_2_0.util.CommerceOrderTypeRelTable;
import com.liferay.commerce.internal.upgrade.v7_2_0.util.CommerceOrderTypeTable;
import com.liferay.commerce.internal.upgrade.v8_4_0.util.CommerceShippingOptionAccountEntryRelTable;
import com.liferay.commerce.internal.upgrade.v8_5_0.CommerceAddressTypeUpgradeProcess;
import com.liferay.commerce.model.impl.CPDAvailabilityEstimateModelImpl;
import com.liferay.commerce.model.impl.CommerceAvailabilityEstimateModelImpl;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CountryLocalService;
import com.liferay.portal.kernel.service.EmailAddressLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.RegionLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.BaseExternalReferenceCodeUpgradeProcess;
import com.liferay.portal.kernel.upgrade.BaseUuidUpgradeProcess;
import com.liferay.portal.kernel.upgrade.CTModelUpgradeProcess;
import com.liferay.portal.kernel.upgrade.DummyUpgradeProcess;
import com.liferay.portal.kernel.upgrade.MVCCVersionUpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true, service = UpgradeStepRegistrator.class
)
public class CommerceServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("Commerce upgrade step registrator started");
		}

		registry.register(
			"1.0.0", "1.1.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "externalReferenceCode VARCHAR(75)"),
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "externalReferenceCode VARCHAR(75)"),
			UpgradeProcessFactory.addColumns(
				"CommerceOrderNote", "externalReferenceCode VARCHAR(75)"));

		registry.register(
			"1.1.0", "1.2.0", new CommerceSubscriptionUpgradeProcess());

		registry.register(
			"1.2.0", "2.0.0", new CommercePaymentMethodUpgradeProcess());

		registry.register(
			"2.0.0", "2.1.0",
			new com.liferay.commerce.internal.upgrade.v2_1_0.
				CommerceOrderItemUpgradeProcess(
					_cpDefinitionLocalService, _cpInstanceLocalService),
			new CommerceSubscriptionEntryUpgradeProcess(
				_cpDefinitionLocalService, _cpInstanceLocalService),
			new CPDAvailabilityEstimateUpgradeProcess(
				_cpDefinitionLocalService));

		registry.register(
			"2.1.0", "2.2.0",
			new com.liferay.commerce.internal.upgrade.v2_2_0.
				CommerceAccountUpgradeProcess(
					_commerceAccountLocalService,
					_commerceAccountOrganizationRelLocalService,
					_emailAddressLocalService, _organizationLocalService),
			new com.liferay.commerce.internal.upgrade.v2_2_0.
				CommerceOrderUpgradeProcess(
					_commerceAccountLocalService, _userLocalService));

		registry.register(
			"2.2.0", "3.0.0",
			new com.liferay.commerce.internal.upgrade.v3_0_0.
				CommerceSubscriptionCycleEntryUpgradeProcess());

		registry.register(
			"3.0.0", "3.1.0",
			new com.liferay.commerce.internal.upgrade.v3_1_0.
				CommerceOrderUpgradeProcess());

		registry.register(
			"3.1.0", "3.2.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "printedNote VARCHAR(75)",
				"requestedDeliveryDate DATE"),
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "deliveryGroup VARCHAR(75)",
				"shippingAddressId LONG", "printedNote VARCHAR(75)",
				"requestedDeliveryDate DATE"),
			UpgradeProcessFactory.dropColumns(
				CommerceAvailabilityEstimateModelImpl.TABLE_NAME, "groupId"),
			UpgradeProcessFactory.dropColumns("CommerceCountry", "groupId"),
			UpgradeProcessFactory.dropColumns("CommerceRegion", "groupId"),
			UpgradeProcessFactory.dropColumns(
				CPDAvailabilityEstimateModelImpl.TABLE_NAME, "groupId"));

		registry.register(
			"3.2.0", "4.0.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "bookedQuantityId LONG"),
			UpgradeProcessFactory.alterColumnName(
				"CommerceShipmentItem", "commerceWarehouseId",
				"commerceInventoryWarehouseId LONG"));

		registry.register(
			"4.0.0", "4.1.0",
			new CommerceAddressUpgradeProcess(_classNameLocalService),
			new com.liferay.commerce.internal.upgrade.v4_1_0.
				CommerceOrderItemUpgradeProcess(),
			new com.liferay.commerce.internal.upgrade.v4_1_0.
				CommerceCountryUpgradeProcess());

		registry.register(
			"4.1.0", "4.1.1",
			UpgradeProcessFactory.alterColumnType(
				"CommerceAddress", "name", "VARCHAR(255) null"),
			UpgradeProcessFactory.alterColumnType(
				"CommerceAddress", "street1", "VARCHAR(255) null"),
			UpgradeProcessFactory.alterColumnType(
				"CommerceAddress", "street2", "VARCHAR(255) null"),
			UpgradeProcessFactory.alterColumnType(
				"CommerceAddress", "street3", "VARCHAR(255) null"));

		registry.register("4.1.1", "4.2.0", new DummyUpgradeProcess());

		registry.register(
			"4.2.0", "4.2.1",
			UpgradeProcessFactory.alterColumnType(
				"CommerceOrder", "printedNote", "STRING null"),
			UpgradeProcessFactory.alterColumnType(
				"CommerceOrderItem", "printedNote", "STRING null"));

		registry.register(
			"4.2.1", "4.3.0", new CommerceOrderDateUpgradeProcess());

		registry.register(
			"4.3.0", "4.4.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "manuallyAdjusted BOOLEAN"),
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "manuallyAdjusted BOOLEAN"));

		registry.register(
			"4.4.0", "4.5.0",
			UpgradeProcessFactory.addColumns(
				"CommerceAddress", "externalReferenceCode VARCHAR(75)"));

		registry.register(
			"4.5.0", "4.5.1",
			new CommerceShippingMethodUpgradeProcess(
				_classNameLocalService, _groupLocalService));

		registry.register(
			"4.5.1", "4.6.0", new DummyUpgradeProcess(),
			UpgradeProcessFactory.alterColumnType(
				"CommerceShipment", "shippingOptionName", "TEXT"),
			UpgradeProcessFactory.addColumns(
				"CommerceSubscriptionEntry",
				"deliverySubscriptionLength INTEGER",
				"deliverySubscriptionType VARCHAR(75)",
				"deliverySubTypeSettings TEXT", "deliveryCurrentCycle LONG",
				"deliveryMaxSubscriptionCycles LONG",
				"deliverySubscriptionStatus INTEGER",
				"deliveryLastIterationDate DATE",
				"deliveryNextIterationDate DATE", "deliveryStartDate DATE"));

		registry.register("4.6.0", "4.7.0", new DummyUpgradeProcess());

		registry.register(
			"4.7.0", "4.8.1", new CommerceOrderStatusesUpgradeProcess());

		registry.register(
			"4.8.1", "4.9.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "subtotalWithTaxAmount DECIMAL(30,16)",
				"subtotalDiscountWithTaxAmount DECIMAL(30,16)",
				"subtotalDiscountPctLev1WithTax DECIMAL(30,16)",
				"subtotalDiscountPctLev2WithTax DECIMAL(30,16)",
				"subtotalDiscountPctLev3WithTax DECIMAL(30,16)",
				"subtotalDiscountPctLev4WithTax DECIMAL(30,16)",
				"shippingWithTaxAmount DECIMAL(30,16)",
				"shippingDiscountWithTaxAmount DECIMAL(30,16)",
				"shippingDiscountPctLev1WithTax DECIMAL(30,16)",
				"shippingDiscountPctLev2WithTax DECIMAL(30,16)",
				"shippingDiscountPctLev3WithTax DECIMAL(30,16)",
				"shippingDiscountPctLev4WithTax DECIMAL(30,16)",
				"totalWithTaxAmount DECIMAL(30,16)",
				"totalDiscountWithTaxAmount DECIMAL(30,16)",
				"totalDiscountPctLev1WithTax DECIMAL(30,16)",
				"totalDiscountPctLev2WithTax DECIMAL(30,16)",
				"totalDiscountPctLev3WithTax DECIMAL(30,16)",
				"totalDiscountPctLev4WithTax DECIMAL(30,16)"),
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "parentCommerceOrderItemId LONG",
				"unitPriceWithTaxAmount DECIMAL(30,16)",
				"promoPriceWithTaxAmount DECIMAL(30,16)",
				"discountWithTaxAmount DECIMAL(30,16)",
				"finalPriceWithTaxAmount DECIMAL(30,16)",
				"discountPctLevel1WithTaxAmount DECIMAL(30,16)",
				"discountPctLevel2WithTaxAmount DECIMAL(30,16)",
				"discountPctLevel3WithTaxAmount DECIMAL(30,16)",
				"discountPctLevel4WithTaxAmount DECIMAL(30,16)",
				"commercePriceListId LONG"));

		registry.register(
			"4.9.0", "4.9.1",
			new com.liferay.commerce.internal.upgrade.v4_9_1.
				CommerceOrderUpgradeProcess());

		registry.register(
			"4.9.1", "4.10.0",
			new com.liferay.commerce.internal.upgrade.v4_10_0.
				CommerceOrderItemUpgradeProcess());

		registry.register(
			"4.10.0", "5.0.0",
			UpgradeProcessFactory.alterColumnName(
				"CommerceAddress", "commerceCountryId", "countryId LONG"),
			UpgradeProcessFactory.alterColumnName(
				"CommerceAddress", "commerceRegionId", "regionId LONG"),
			UpgradeProcessFactory.alterColumnName(
				"CommerceAddressRestriction", "commerceCountryId",
				"countryId LONG"));

		registry.register(
			"5.0.0", "5.0.1",
			new CommercePermissionUpgradeProcess(
				_resourceActionLocalService, _resourcePermissionLocalService));

		registry.register(
			"5.0.1", "6.0.0",
			new com.liferay.commerce.internal.upgrade.v6_0_0.
				CommerceCountryUpgradeProcess(_countryLocalService),
			new com.liferay.commerce.internal.upgrade.v6_0_0.
				CommerceRegionUpgradeProcess(_regionLocalService));

		registry.register(
			"6.0.0", "7.0.0",
			new com.liferay.commerce.internal.upgrade.v7_0_0.
				CommerceAddressUpgradeProcess(
					_addressLocalService, _accountEntryLocalService,
					_listTypeLocalService, _phoneLocalService));

		registry.register(
			"7.0.0", "7.1.0",
			new com.liferay.commerce.internal.upgrade.v7_1_0.
				CommerceOrderUpgradeProcess());

		registry.register(
			"7.1.0", "7.2.0", CommerceOrderTypeTable.create(),
			CommerceOrderTypeRelTable.create());

		registry.register(
			"7.2.0", "7.3.0",
			new MVCCVersionUpgradeProcess() {

				@Override
				protected String[] getModuleTableNames() {
					return new String[] {
						"CPDAvailabilityEstimate", "CPDefinitionInventory",
						"CommerceAddressRestriction",
						"CommerceAvailabilityEstimate", "CommerceOrder",
						"CommerceOrderItem", "CommerceOrderNote",
						"CommerceOrderPayment", "CommerceOrderType",
						"CommerceOrderTypeRel", "CommerceShipment",
						"CommerceShipmentItem", "CommerceShippingMethod",
						"CommerceSubscriptionEntry"
					};
				}

			});

		registry.register(
			"7.3.0", "8.0.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "CPMeasurementUnitId LONG",
				"decimalQuantity DECIMAL(30, 16) null"));

		registry.register("8.0.0", "8.0.1", new DummyUpgradeProcess());

		registry.register(
			"8.0.1", "8.1.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrder", "deliveryCommerceTermEntryId LONG",
				"deliveryCTermEntryDescription TEXT null",
				"deliveryCommerceTermEntryName VARCHAR(75) null",
				"paymentCommerceTermEntryId LONG",
				"paymentCTermEntryDescription TEXT null",
				"paymentCommerceTermEntryName VARCHAR(75) null"));

		registry.register(
			"8.1.0", "8.1.1",
			new com.liferay.commerce.internal.upgrade.v8_1_1.
				CommerceAddressUpgradeProcess());

		registry.register(
			"8.1.1", "8.2.0",
			UpgradeProcessFactory.addColumns(
				"CommerceShipment", "externalReferenceCode VARCHAR(75)"),
			UpgradeProcessFactory.addColumns(
				"CommerceShipmentItem", "externalReferenceCode VARCHAR(75)"));

		registry.register(
			"8.2.0", "8.3.0",
			new CTModelUpgradeProcess("CPDefinitionInventory"));

		registry.register(
			"8.3.0", "8.4.0",
			CommerceShippingOptionAccountEntryRelTable.create());

		registry.register(
			"8.4.0", "8.5.0",
			new CommerceAddressTypeUpgradeProcess(_listTypeLocalService));

		registry.register(
			"8.5.0", "8.6.0",
			new BaseUuidUpgradeProcess() {

				@Override
				protected String[][] getTableAndPrimaryKeyColumnNames() {
					return new String[][] {
						{"CommerceOrderItem", "commerceOrderItemId"},
						{"CommerceOrderNote", "commerceOrderNoteId"},
						{"CommerceOrderType", "commerceOrderTypeId"},
						{"CommerceOrderTypeRel", "commerceOrderTypeRelId"},
						{"CommerceShipment", "commerceShipmentId"},
						{"CommerceShipmentItem", "commerceShipmentItemId"}
					};
				}

			});

		registry.register(
			"8.6.0", "8.6.1",
			new BaseExternalReferenceCodeUpgradeProcess() {

				@Override
				protected String[][] getTableAndPrimaryKeyColumnNames() {
					return new String[][] {
						{"CommerceOrder", "commerceOrderId"},
						{"CommerceOrderItem", "commerceOrderItemId"},
						{"CommerceOrderNote", "commerceOrderNoteId"},
						{"CommerceOrderType", "commerceOrderTypeId"},
						{"CommerceOrderTypeRel", "commerceOrderTypeRelId"}
					};
				}

			});

		registry.register(
			"8.6.1", "8.7.0",
			UpgradeProcessFactory.addColumns(
				"CommerceShipment", "trackingURL STRING null"),
			UpgradeProcessFactory.addColumns(
				"CommerceShippingMethod", "trackingURL STRING null"));

		registry.register(
			"8.7.0", "8.8.0",
			new com.liferay.commerce.internal.upgrade.v8_8_0.
				CommercePermissionUpgradeProcess(
					_resourceActionLocalService,
					_resourcePermissionLocalService, _roleLocalService));

		registry.register(
			"8.8.0", "8.9.0",
			UpgradeProcessFactory.addColumns(
				"CommerceOrderItem", "discountManuallyAdjusted BOOLEAN",
				"priceManuallyAdjusted BOOLEAN"));

		if (_log.isInfoEnabled()) {
			_log.info("Commerce upgrade step registrator finished");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceServiceUpgradeStepRegistrator.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountOrganizationRelLocalService
		_commerceAccountOrganizationRelLocalService;

	@Reference
	private CountryLocalService _countryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

	@Reference
	private EmailAddressLocalService _emailAddressLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference
	private RegionLocalService _regionLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}