/*
* Copyright 2014 Basis Technology Corp.
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
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * A reference to a "real world" entity. Each entity in a document is
 * the result of resolving one or more {@link Mention}s -- a {@linkplain Mention}
 * is a span of text that mentions an entity, while an {@linkplain Entity}
 * describes the entity itself.
 * <p>
 * Each {@linkplain Entity} provides:
 * <ul>
 *     <li>a list of {@linkplain Mention}s</li>
 *     <li>(optionally) an ID that associates the entity some external knowledge base, e.g.
 * <a href="http://www.wikidata.org/wiki/Q23">Q23</a> from Wikidata.</li>
 * <li>(optionally) a sentiment category associated with it
 * (e.g. "positive", "negative", "neutral").</li>
 * </ul>
 * Entities are not spans of text.
 */
public class Entity extends BaseAttribute {
    private List<Mention> mentions;
    private final Integer headMentionIndex;
    private final String type;
    private final String entityId;
    private final Double confidence;
    private final List<CategorizerResult> sentiment;

    protected Entity(List<Mention> mentions,
                     Integer headMentionIndex,
                     String type,
                     String entityId,
                     Double confidence,
                     List<CategorizerResult> sentiment,
                     Map<String, Object> extendedProperties) {
        super(extendedProperties);
        this.mentions = listOrNull(mentions);
        this.headMentionIndex = headMentionIndex;
        this.type = type;
        this.entityId = entityId;
        this.confidence = confidence;
        this.sentiment = listOrNull(sentiment);
    }

    /**
     * Returns the unique identifier of this entity.
     *
     * @return the unique identifier of this entity
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * @return the list of mentions that support this entity.
     */
    public List<Mention> getMentions() {
        return mentions;
    }

    /**
     * Return the head mention index, if any. The head mention is the mention judged to be
     * the best representation of the entity itself. This returns {@code null} if no
     * mention is designated as head.
     * @return the index of the head mention.
     */
    public Integer getHeadMentionIndex() {
        return headMentionIndex;
    }

    /**
     * Returns the confidence for this resolved entity, or null if there is none.
     *
     * @return the confidence for this resolved entity, or null if there is none
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * Returns the sentiment of this entity, or null if not computed.
     *
     * @return the sentiment of this entity, or null if not computed.
     */
    public List<CategorizerResult> getSentiment() {
        return sentiment;
    }

    /**
     * If there is a type established for the entity, return the entity.
     * @return the type.
     */
    public String getType() {
        return type;
    }

    // hmm, what *really* belongs in equals and hashCode?  maybe just entityId?
    // for now, I'm following suit by using all fields.
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

        Entity that = (Entity) o;

        if (mentions != null ? !mentions.equals(that.mentions) : that.mentions != null) {
            return false;
        }

        if (entityId != null ? !entityId.equals(that.entityId) : that.entityId != null) {
            return false;
        }

        if (confidence != null ? !confidence.equals(that.confidence) : that.confidence != null) {
            return false;
        }

        return sentiment != null ? sentiment.equals(that.sentiment) : that.sentiment == null;
    }

    @Override
    public int hashCode() {
        // generated by intellij
        //CHECKSTYLE:OFF
        int result = super.hashCode();
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (mentions != null ? mentions.hashCode() : 0);
        result = 31 * result + (confidence != null ? confidence.hashCode() : 0);
        result = 31 * result + (sentiment != null ? sentiment.hashCode() : 0);
        return result;
        //CHECKSTYLE:ON
    }

    @Override
    protected Objects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("entityId", entityId)
                .add("mentions", mentions)
                .add("confidence", confidence)
                .add("sentiment", sentiment);
    }

    /**
     * A builder for resolved entities.
     */
    public static class Builder extends BaseAttribute.Builder<Entity, Entity.Builder> {
        private String entityId;
        private String type;
        private List<Mention> mentions;
        private Integer headMentionIndex;
        private Double confidence;
        private List<CategorizerResult> sentiment;

        /**
         * Constructs a builder from the required values.
         */
        public Builder() {
            mentions = Lists.newArrayList();
            sentiment = Lists.newArrayList();
        }

        /**
         * Constructs a builder by copying values from an existing resolved entity.
         *
         * @param toCopy the object to create
         * @adm.ignore
         */
        public Builder(Entity toCopy) {
            super(toCopy);
            mentions = Lists.newArrayList();
            addAllToList(mentions, toCopy.mentions);
            this.entityId = toCopy.entityId;
            this.confidence = toCopy.confidence;
            this.sentiment = toCopy.sentiment;
            this.type = toCopy.type;
        }

        /**
         * Specifies the entity unique ID.
         *
         * @param entityId the ID
         * @return this
         */
        public Builder entityId(String entityId) {
            this.entityId = entityId;
            return this;
        }

        /**
         * Add one mention to the mentions.
         * @param mention the mention.
         * @return this.
         */
        public Builder mention(Mention mention) {
            this.mentions.add(mention);
            return this;
        }

        /**
         * Specifies the index of the head mention in the list of mentions, if any.
         * @param headMentionIndex the index.
         * @return this.
         */
        public Builder headMentionIndex(Integer headMentionIndex) {
            this.headMentionIndex = headMentionIndex;
            return this;
        }

        /**
         * Specifies the confidence value.
         *
         * @param confidence the confidence value
         * @return this
         */
        public Builder confidence(double confidence) {
            this.confidence = confidence;
            return this;
        }

        /**
         * Specifies the sentiment.
         *
         * @param sentiment the sentiment
         * @return this
         */
        public Builder sentiment(CategorizerResult sentiment) {
            this.sentiment.add(sentiment);
            return this;
        }

        /**
         * Specify the type for this entity.
         * @param type the type
         * @return this
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Returns an immutable resolved entity from the current state of the builder.
         *
         * @return the new resolved entity
         */
        public Entity build() {
            return new Entity(mentions, headMentionIndex, type, entityId, confidence,
                            sentiment, buildExtendedProperties());
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
