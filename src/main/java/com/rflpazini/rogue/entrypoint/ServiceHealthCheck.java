package com.rflpazini.rogue.entrypoint;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class ServiceHealthCheck implements HealthCheck {

  @Override
  public HealthCheckResponse call() {

    return HealthCheckResponse.named(ServiceHealthCheck.class.getSimpleName()).up().build();
  }
}
