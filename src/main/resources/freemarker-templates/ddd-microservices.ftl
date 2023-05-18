<#--
 This template generates a JHipster JDL file.
 Please consult our online tutorial https://contextmapper.org/docs/jhipster-microservice-generation/ to learn how to use it.
-->
<#-- 
 variables to collect entity names and references: 
-->
<#assign allEntityNames = [] />
<#assign oneToManyRefs = [] />
<#assign oneToOneRefs = [] />
<#assign contextNames = [] />
<#assign gateway = false />
<#assign deployment = false />
<#assign threeTier = false />

<#-- 
 counter to give microservices different ports: (8081, 8082, 8083, ...) 
-->
<#assign portCounter = 8080 />
<#assign contexts = filterStructuralBoundedContexts(boundedContexts) />
<#-- 
 loop to collect entity data per Bounded Context (BC) and create application plus microservice for each BC
-->

<#list contexts as bc>
<#assign entities = [] />
<#assign valueObjects = [] />
<#assign entityNames = [] />
<#assign enums = [] />
<#assign events = [] />
<#assign commands = [] />
<#list bc.aggregates as agg>
	<#assign entities = entities + agg.domainObjects?filter(dob -> instanceOf(dob, Entity))>
	<#assign valueObjects = valueObjects + agg.domainObjects?filter(dob -> instanceOf(dob, ValueObject))>
	<#assign enums = enums + agg.domainObjects?filter(dob -> instanceOf(dob, Enum))>
	<#if !threeTier>
		<#assign events = events + agg.domainObjects?filter(dob -> instanceOf(dob, DomainEvent))>
		<#assign commands = commands + agg.domainObjects?filter(dob -> instanceOf(dob, CommandEvent))>
	</#if>
</#list>
<#assign entityNames = entities?map(e -> e.name)>
<#assign eventNames = events?map(e -> e.name)>
<#assign commandNames = commands?map(e -> e.name)>
<#assign enumNames = enums?map(e -> e.name)>
<#assign allEntityNames = allEntityNames + entityNames>
<#assign contextNames=contextNames + [bc.name]/>

<#if entities?has_content>

/* Bounded Context ${bc.name} */
<#list enums as anEnum>
<#assign enumValues = anEnum.values?map(e-> e.name)?join(" ")/>
enum ${anEnum.name} {
	${enumValues}
}
</#list>

<#assign entitiesAndValueObjects = entities + valueObjects + events + commands>
<#list entitiesAndValueObjects as entity>

<#list entity.references as reference>
	<#if reference.domainObjectType?has_content && (instanceOf(reference.domainObjectType, Entity) || instanceOf(reference.domainObjectType, ValueObject))>
		<#if reference.collectionType?has_content && reference.collectionType.name() != "NONE">
			<#assign oneToManyRefs = oneToManyRefs + [ entity.name + "{" + reference.name + "} to " + reference.domainObjectType.name ]>
		<#else>
			<#assign oneToOneRefs = oneToOneRefs + [ entity.name + "{"+ reference.name + "} to " + reference.domainObjectType.name ]>
		</#if>
	</#if>
</#list>

<#if instanceOf(entity, ValueObject)>
@readOnly
</#if>
<#if instanceOf(entity, Entity) && threeTier>
@service(serviceClass)
</#if>
@dto(mapstruct)
entity ${entity.name} {
	<#list entity.attributes as attribute>
	${attribute.name} ${mapAttributeType(attribute.type)}
	</#list>
	<#list entity.references as reference>
	<#if reference.domainObjectType?has_content && instanceOf(reference.domainObjectType, Enum)>
	${reference.name} ${reference.domainObjectType.name}
	</#if>
	</#list>
}

</#list>

<#list events as event>
@readOnly
@dto(mapstruct)
entity ${event.name}Event {
	<#list event.attributes as attribute>
	${attribute.name} ${mapAttributeType(attribute.type)}
	</#list>
}

</#list>

<#list commands as command>
@service(serviceClass)
entity ${command.name}Command {
	<#list command.attributes as attribute>
	${attribute.name} ${mapAttributeType(attribute.type)}
	</#list>
}

</#list>

microservice ${entityNames?join(", ")} with ${bc.name}<#lt>
</#if>
<#assign allValueObjectAndEntityNames=entitiesAndValueObjects?map(obj->obj.name) />
<#assign allObjectNames=allValueObjectAndEntityNames + eventNames?map(name->"${name}Event") />
<#assign allObjectNames=allObjectNames + commandNames?map(name->"${name}Command") />
<#assign portCounter++ />
application {
	config {
		baseName ${bc.name}
		packageName org.contextmapper.generated.${bc.name?lower_case}
		applicationType microservice
		serverPort ${portCounter?int?c}
		enableSwaggerCodegen true
		serviceDiscoveryType consul
		prodDatabaseType postgresql
	}
	<#if allObjectNames?has_content>
	entities ${allObjectNames?join(", ")}
	</#if>
}
</#list>

<#--
 here we print the collected references as relationships:
-->
/* relationships */
<#if oneToManyRefs?has_content>
	relationship OneToMany {<#lt>
		<#list oneToManyRefs as reference>
			${reference}
		</#list>
	}<#lt>
</#if>
<#if oneToOneRefs?has_content>
	relationship OneToOne {<#lt>
		<#list oneToOneRefs as reference>
			${reference}
		</#list>
	}<#lt>
</#if>

<#if gateway>
<#--
 create a microservice gateway (user interface)
-->
/* microservice gateway app */
application {
	config {
		baseName gateway,
		packageName org.contextmapper.generated.gateway
		applicationType gateway
		serverPort 8080
	}
	<#if allEntityNames?has_content>
	entities ${allEntityNames?join(", ")}
	</#if>
}
</#if>

<#if deployment>
<#--
Configure deployment
-->
deployment {
	deploymentType docker-compose
	appsFolders [${contextNames?join(", ")}]
	dockerRepositoryName "loicroux"
	serviceDiscoveryType consul
}
</#if>

<#-- Data type mapping: -->
<#function mapAttributeType inputType>
  <#if inputType == "String">
  	<#return "String">
  <#elseif inputType == "int" || inputType == "Integer">
  	<#return "Integer">
  <#elseif inputType == "long" || inputType == "Long">
  	<#return "Long">
  <#elseif inputType == "boolean" || inputType == "Boolean">
  	<#return "Boolean">
  <#elseif inputType == "Date" || inputType == "DateTime" || inputType == "Timestamp">
  	<#return "LocalDate">
  <#elseif inputType == "BigDecimal" || inputType == "BigInteger">
  	<#return "BigDecimal">
  <#elseif inputType == "double" || inputType == "Double">
  	<#return "Double">
  <#elseif inputType == "float" || inputType == "Float">
  	<#return "Float">
  <#elseif inputType == "Key">
  	<#return "UUID">
  <#elseif inputType == "Blob" || inputType =="Object[]">
  	<#return "Blob">
  <#elseif inputType == "Clob">
  	<#return "TextBlob">
  <#else>
  	<#return "Blob">
  </#if>
</#function>
