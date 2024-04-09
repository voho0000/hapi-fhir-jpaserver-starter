package ca.uhn.fhir.jpa.starter.ips;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.ips.api.IIpsGenerationStrategy;
import ca.uhn.fhir.jpa.ips.generator.IIpsGeneratorSvc;
import ca.uhn.fhir.jpa.ips.generator.IpsGeneratorSvcImpl;
import ca.uhn.fhir.jpa.ips.jpa.DefaultJpaIpsGenerationStrategy;
import ca.uhn.fhir.jpa.ips.provider.IpsOperationProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@Conditional(IpsConfigCondition.class)
public class StarterIpsConfig {
	@Bean
	IIpsGenerationStrategy ipsGenerationStrategy() {
		return new DefaultJpaIpsGenerationStrategy();
	}

	@Bean
	public IpsOperationProvider ipsOperationProvider(IIpsGeneratorSvc theIpsGeneratorSvc) {
		return new IpsOperationProvider(theIpsGeneratorSvc);
	}

	@Bean
	public IIpsGeneratorSvc ipsGeneratorSvcImpl(
			FhirContext theFhirContext, IIpsGenerationStrategy theGenerationStrategy) {
		return new IpsGeneratorSvcImpl(theFhirContext, theGenerationStrategy);
	}
}