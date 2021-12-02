package in.co.ad.customer.portal.backend.repository;

import in.co.ad.customer.portal.backend.domain.CustomerDo;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerDo, Long> {
}
