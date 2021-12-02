package in.co.ad.customer.portal.backend.controller;

import in.co.ad.customer.portal.backend.assemble.CustomerDetailsAssembler;
import in.co.ad.customer.portal.backend.domain.CustomerDo;
import in.co.ad.customer.portal.backend.dto.CustomerDto;
import in.co.ad.customer.portal.backend.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerDetailsAssembler assembleOrderDetailsDto;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/", produces = {"application/json"},consumes = {"application/json"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomerDto> publishOrderEvent(@RequestBody CustomerDto customerDto) {
        try {

            CustomerDo customerDo = assembleOrderDetailsDto.assembleCustomerDetailsDomain(customerDto);

            customerRepository.save(customerDo);
            customerDto = assembleOrderDetailsDto.assembleCustomerDetailsDto(customerDo);

            return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/", produces = {"application/json"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<CustomerDto>> getAllCustomers() {

        List<CustomerDto> customerDtos = null;
        try {
            Iterable<CustomerDo> customerDos = customerRepository.findAll();

            customerDtos = assembleOrderDetailsDto.assembleCustomerDetailsDto(customerDos);

            return new ResponseEntity<List<CustomerDto>>(customerDtos, HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return new ResponseEntity<List<CustomerDto>>(customerDtos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
