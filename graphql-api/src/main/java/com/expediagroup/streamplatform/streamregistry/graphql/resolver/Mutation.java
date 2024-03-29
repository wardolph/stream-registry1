/**
 * Copyright (C) 2018-2019 Expedia, Inc.
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
package com.expediagroup.streamplatform.streamregistry.graphql.resolver;

import static com.expediagroup.streamplatform.streamregistry.graphql.resolver.KeyConvertor.convert;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.stereotype.Component;

import com.expediagroup.streamplatform.streamregistry.graphql.model.GraphQLInfrastructure;
import com.expediagroup.streamplatform.streamregistry.graphql.model.GraphQLSchema;
import com.expediagroup.streamplatform.streamregistry.graphql.model.GraphQLStream;
import com.expediagroup.streamplatform.streamregistry.model.Domain;
import com.expediagroup.streamplatform.streamregistry.model.Schema;
import com.expediagroup.streamplatform.streamregistry.model.Stream;
import com.expediagroup.streamplatform.streamregistry.service.Service;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

  private final Service<Domain, Domain.Key> domainService;
  private final Service<Schema, Schema.Key> schemaService;
  private final Service<Stream, Stream.Key> streamService;

  public boolean upsertDomain(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration) {
    domainService.upsert(
        Domain
            .builder()
            .name(name)
            .owner("root") //TODO inject user
            .description(description)
            .tags(tags)
            .type(type)
            .configuration(configuration)
            .build()
    );
    return true;
  }

  public boolean upsertSchema(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      String domain) {
    schemaService.upsert(
        Schema
            .builder()
            .name(name)
            .owner("root") //TODO inject user
            .description(description)
            .tags(tags)
            .type(type)
            .configuration(configuration)
            .domainKey(new Domain.Key(domain))
            .build()
    );
    return true;
  }

  public boolean upsertStream(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      String domain,
      Integer version,
      GraphQLSchema.Key schema) {
    streamService.upsert(
        Stream
            .builder()
            .name(name)
            .owner("root") //TODO inject user
            .description(description)
            .tags(tags)
            .type(type)
            .configuration(configuration)
            .domainKey(new Domain.Key(domain))
            .version(version)
            .schemaKey(convert(schema))
            .build()
    );
    return true;
  }

  public boolean upsertZone(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertInfrastructure(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      String zone) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertProducer(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      GraphQLStream.Key stream,
      List<String> zones) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertConsumer(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      GraphQLStream.Key stream,
      List<String> zones) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertStreamBinding(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      GraphQLStream.Key stream,
      GraphQLInfrastructure.Key infrastructure) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertProducerBinding(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      String producer,
      List<String> zones) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  public boolean upsertConsumerBinding(
      String name,
      String description,
      Map<String, String> tags,
      String type,
      ObjectNode configuration,
      String consumer,
      List<String> zones) {
    // TODO implement
    throw new UnsupportedOperationException("Not yet implemented.");
  }
}
