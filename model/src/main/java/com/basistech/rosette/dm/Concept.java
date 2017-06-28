/*
* Copyright 2017 Basis Technology Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.basistech.rosette.dm;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Map;

/**
 * A reference to a high-level "concept" of a document.  A concept can be an abstract
 * or concrete topic that is highly relevant to the document in question.  Concepts
 * may or may not be explicitly referenced in the document, and as such do not refer to
 * any specific span of text.
 * Each {@linkplain Concept} provides:
 * <ul>
 *     <li>the name of the specific concept</li>
 *     <li>a salience value associated with the concept</li>
 *     <li>an ID that associates the concept some external knowledge base, e.g.
 * <a href="http://www.wikidata.org/wiki/Q23">Q23</a> from Wikidata.</li>
 * </ul>
 */
public class Concept extends BaseAttribute implements Serializable {
    private static final long serialVersionUID = 222L;
    private final String concept;
    private final Double salience;
    private final String conceptId;

    protected Concept(String concept, Double salience, String conceptId, Map<String, Object> extendedProperties) {
        super(extendedProperties);
        this.concept = concept;
        this.salience = salience;
        this.conceptId = conceptId;
    }

    /**
     * Returns the name for the concept
     * @return concept the name of the concept
     */
    public String getConcept() {
        return concept;
    }

    /**
     * Returns the salience associated with this concept
     * @return the salience
     */
    public Double getSalience() {
        return salience;
    }

    /**
     * Returns the ID associated with the concept
     * @return the ID associated with the concept
     */
    public String getConceptId() {
        return conceptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Concept that = (Concept) o;
        return java.util.Objects.equals(concept, that.concept)
                && java.util.Objects.equals(salience, that.salience)
                && java.util.Objects.equals(conceptId, that.conceptId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), concept, salience, conceptId);
    }

    @Override
    protected Objects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("concept", concept)
                .add("salience", salience)
                .add("conceptId", conceptId);
    }

    /**
     * A Builder for concepts
     */
    public static class Builder extends BaseAttribute.Builder<Concept, Concept.Builder> {
        protected String concept;
        protected Double salience;
        protected String conceptId;

        /**
         * Construct a builder out of the required properties
         * @param concept the name of the concept
         * @param conceptId the ID associated with the concept
         */
        public Builder(String concept, String conceptId) {
            this.concept = concept;
            this.conceptId = conceptId;
        }

        /**
         * Constructs a builder by copying values from an existing concept
         *
         * @param toCopy the object to copy from
         * @adm.ignore
         */
        public Builder(Concept toCopy) {
            super(toCopy);
            this.concept = toCopy.getConcept();
            this.salience = toCopy.getSalience();
            this.conceptId = toCopy.getConceptId();
        }

        /**
         * Specify the name of the concept
         * @param concept the name of the concept
         * @return this
         */
        public Builder concept(String concept) {
            this.concept = concept;
            return this;
        }

        /**
         * Specify the salience associated with this concept
         * @param salience the salience associated with the concept
         * @return this
         */
        public Builder salience(Double salience) {
            this.salience = salience;
            return this;
        }

        /**
         * Specify the unique ID associated with this concept
         * @param conceptId the unique ID associated with the concept
         * @return this
         */
        public Builder conceptId(String conceptId) {
            this.conceptId = conceptId;
            return this;
        }

        /**
         * Returns an immutable concept based on the content of the builder
         * @return the new concept
         */
        public Concept build() {
            return new Concept(concept, salience, conceptId, buildExtendedProperties());
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }

}