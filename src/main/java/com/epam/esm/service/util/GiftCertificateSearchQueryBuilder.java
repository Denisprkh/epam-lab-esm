package com.epam.esm.service.util;

import java.util.HashMap;
import java.util.Map;

public class GiftCertificateSearchQueryBuilder {

    private static final String FIND_BY_PART_OF_NAME_QUERY = " gift_certificate.name REGEXP ? ";
    private static final String FIND_BY_TAG_NAME_QUERY = " JOIN gift_certificates_tag ON gift_certificates_tag.gift_certificate_id = " +
            "gift_certificate.gift_certificate_id JOIN tag ON tag.tag_id = gift_certificates_tag.tag_id WHERE " +
            "tag.name = ? ";
    private static final String FIND_BY_PART_OF_DESCRIPTION_QUERY = " gift_certificate.description REGEXP ? ";
    private static final String BY_NAME_ASCENDING = "gift_certificate.name ASC ";
    private static final String BY_NAME_DESCENDING = "gift_certificate.name DESC ";
    private static final String BY_DATE_ASCENDING = "gift_certificate.create_date ASC ";
    private static final String BY_DATE_DESCENDING = "gift_certificate.create_date DESC ";
    private static final String PARAM_GIFT_CERTIFICATE_NAME = "giftCertificateName";
    private static final String PARAM_GIFT_CERTIFICATE_DESCRIPTION = "giftCertificateDescription";
    private static final String PARAM_ORDER_BY = "order";
    private static final String PARAM_TAG_NAME = "tagName";
    private static final String ORDER_BY_NAME_ASC_PARAM = "name:asc";
    private static final String ORDER_BY_NAME_DESC_PARAM = "name:desc";
    private static final String ORDER_BY_CREATE_DATE_ASC_PARAM = "createDate:asc";
    private static final String ORDER_BY_CREATE_DATE_DESC_PARAM = "createDate:desc";
    private static final String ORDER_GIFT_CERTIFICATES_BY = "ORDER BY ";
    private static final String AND_CLAUSE = " AND ";
    private static final String WHERE_CLAUSE = " WHERE";
    private static final String EMPTY_STRING = "";

    private final Map<String, String> searchQueries;
    private final Map<String, String> orderQueries;
    private final StringBuilder queryBuilder;

    public GiftCertificateSearchQueryBuilder() {
        this.searchQueries = new HashMap<>();
        this.orderQueries = new HashMap<>();
        this.queryBuilder = new StringBuilder(EMPTY_STRING);
        init();
    }

    private void init() {
        searchQueries.put(PARAM_TAG_NAME, FIND_BY_TAG_NAME_QUERY);
        searchQueries.put(PARAM_ORDER_BY, ORDER_GIFT_CERTIFICATES_BY);
        searchQueries.put(PARAM_GIFT_CERTIFICATE_NAME, FIND_BY_PART_OF_NAME_QUERY);
        searchQueries.put(PARAM_GIFT_CERTIFICATE_DESCRIPTION, FIND_BY_PART_OF_DESCRIPTION_QUERY);

        orderQueries.put(ORDER_BY_NAME_ASC_PARAM, BY_NAME_ASCENDING);
        orderQueries.put(ORDER_BY_NAME_DESC_PARAM, BY_NAME_DESCENDING);
        orderQueries.put(ORDER_BY_CREATE_DATE_ASC_PARAM, BY_DATE_ASCENDING);
        orderQueries.put(ORDER_BY_CREATE_DATE_DESC_PARAM, BY_DATE_DESCENDING);
    }

    public String buildQuery(Map<String, String> params) {
        if (!params.isEmpty()) {
            appendQuery(params);
            appendSortConditionIfExists(params);
        }
        return queryBuilder.toString();
    }

    private void appendQuery(Map<String, String> params) {
        appendSelectorByTagName(params);
        params.keySet().forEach(key -> {
            String queryCondition = searchQueries.get(key);
            if (!PARAM_ORDER_BY.equals(key)) {
                if (!whereClauseIsRequired()) {
                    queryBuilder.append(AND_CLAUSE);
                } else {
                    queryBuilder.append(WHERE_CLAUSE);
                }
                queryCondition = queryCondition.replaceAll("\\?", "\"" + params.get(key) + "\"");
                queryBuilder.append(queryCondition);
            }
        });
    }

    private boolean appendSelectorByTagName(Map<String, String> params) {
        if (params.containsKey(PARAM_TAG_NAME)) {
            String query = searchQueries.get(PARAM_TAG_NAME).replaceAll("\\?",
                    "\"" + params.get(PARAM_TAG_NAME) + "\"");
            queryBuilder.append(query);
            params.remove(PARAM_TAG_NAME);
            return true;
        }
        return false;
    }

    private boolean whereClauseIsRequired() {
        return !queryBuilder.toString().contains(WHERE_CLAUSE);
    }

    private void appendSortConditionIfExists(Map<String, String> params) {
        if (params.containsKey(PARAM_ORDER_BY)) {
            queryBuilder.append(ORDER_GIFT_CERTIFICATES_BY);
            queryBuilder.append(orderQueries.get(params.get(PARAM_ORDER_BY)));
        }
    }

}
