/*
 *  Copyright 2016 esbtools Contributors and/or its affiliates.
 *
 *  This file is part of esbtools.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.esbtools.eventhandler.lightblue.config;

import org.esbtools.eventhandler.lightblue.LightblueDocumentEventRepositoryConfig;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ThreadSafe
public class MutableLightblueDocumentEventRepositoryConfig implements LightblueDocumentEventRepositoryConfig {
    private Collection<String> canonicalTypesToProcess = Collections.emptySet();
    private int documentEventsBatchSize = 0;
    private Duration processingTimeout = Duration.ofMinutes(10);
    private Duration expireThreshold = Duration.ofMinutes(2);

    /**
     * Uses empty default values, which will configure a repository to never retrieve anything.
     */
    public MutableLightblueDocumentEventRepositoryConfig() {
        this.canonicalTypesToProcess = Collections.emptySet();
        this.documentEventsBatchSize = 0;
        this.processingTimeout = Duration.ofMinutes(10);
        this.expireThreshold = Duration.ofMinutes(2);
    }

    /**
     * Uses provided as initial values.
     */
    public MutableLightblueDocumentEventRepositoryConfig(
            Collection<String> initialCanonicalTypesToProcess,
            int initialDocumentEventsBatchSize, Duration processingTimeout,
            Duration expireThreshold) {
        this.canonicalTypesToProcess = Objects.requireNonNull(initialCanonicalTypesToProcess, "initialCanonicalTypesToProcess");
        this.documentEventsBatchSize = Objects.requireNonNull(initialDocumentEventsBatchSize, "initialDocumentEventsBatchSize");
        this.processingTimeout = Objects.requireNonNull(processingTimeout, "processingTimeout");
        this.expireThreshold = Objects.requireNonNull(expireThreshold, "expireThreshold");
    }

    /**
     * Returns a new {@code Set} with the current state of the canonical types. Updates to
     * configured canonical types will not be visible to the returned {@code Set}.
     */
    @Override
    public Set<String> getCanonicalTypesToProcess() {
        return new HashSet<>(canonicalTypesToProcess);
    }

    public MutableLightblueDocumentEventRepositoryConfig setCanonicalTypesToProcess(
            Collection<String> types) {
        canonicalTypesToProcess = types;
        return this;
    }

    @Override
    public Integer getDocumentEventsBatchSize() {
        return documentEventsBatchSize;
    }

    @Override
    public Duration getDocumentEventProcessingTimeout() {
        return processingTimeout;
    }

    public MutableLightblueDocumentEventRepositoryConfig setDocumentEventProcessingTimeout(
            Duration processingTimeout) {
        this.processingTimeout = processingTimeout;
        return this;
    }

    @Override
    public Duration getDocumentEventExpireThreshold() {
        return expireThreshold;
    }

    public MutableLightblueDocumentEventRepositoryConfig setDocumentEventExpireThreshold(
            Duration expireThreshold) {
        this.expireThreshold = expireThreshold;
        return this;
    }

    public MutableLightblueDocumentEventRepositoryConfig setDocumentEventsBatchSize(int batchSize) {
        documentEventsBatchSize = batchSize;
        return this;
    }
}
