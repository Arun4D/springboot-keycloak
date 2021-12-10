package in.co.ad.customer.portal.backend.controller;

import in.co.ad.customer.portal.backend.assemble.CustomerDetailsAssembler;
import in.co.ad.customer.portal.backend.domain.CustomerDo;
import in.co.ad.customer.portal.backend.dto.CustomerDto;
import in.co.ad.customer.portal.backend.exception.CustomerBusinessException;
import in.co.ad.customer.portal.backend.exception.InvalidInputException;
import in.co.ad.customer.portal.backend.repository.CustomerRepository;
import in.co.ad.customer.portal.backend.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {

    private final CustomerDetailsAssembler customerDetailsAssembler;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerDetailsAssembler customerDetailsAssembler) {
        this.customerRepository = customerRepository;
        this.customerDetailsAssembler = customerDetailsAssembler;
    }



    @RequestMapping(value = "/", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomerDto> publishOrderEvent(@RequestBody CustomerDto customerDto) throws Exception {

        if (null == customerDto) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_DATA);
        }
        CustomerDo customerDo = customerDetailsAssembler.assembleCustomerDetailsDomain(customerDto);

        customerRepository.save(customerDo);
        customerDto = customerDetailsAssembler.assembleCustomerDetailsDto(customerDo);

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/", produces = {"application/json"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<CustomerDto>> getAllCustomers() throws Exception {

        List<CustomerDto> customerDtos;
        Iterable<CustomerDo> customerDos = customerRepository.findAll();

        customerDtos = customerDetailsAssembler.assembleCustomerDetailsDto(customerDos);

        if (null == customerDtos) {
            throw new CustomerBusinessException(ErrorMessage.NO_DATA_FOUND);
        }

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) throws Exception {

        CustomerDto customerDto;
        if (id <= 0) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT_DATA);
        }

        Optional<CustomerDo> customerDo = customerRepository.findById(id);

        customerDto = customerDo.map(customerDetailsAssembler::assembleCustomerDetailsDto).orElse(null);

        if (null == customerDto) {
            throw new CustomerBusinessException(ErrorMessage.NO_DATA_FOUND);
        }

        return new ResponseEntity<>(customerDto, HttpStatus.OK);

    }
}
