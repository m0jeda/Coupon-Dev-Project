package health;

import com.codahale.metrics.health.HealthCheck;

public class ServiceHealthCheck extends HealthCheck {
    private final String template;

    public ServiceHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("check doesn't include a name");
        }
        return Result.healthy();
    }
}
