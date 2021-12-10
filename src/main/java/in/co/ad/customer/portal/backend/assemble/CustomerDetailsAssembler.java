package in.co.ad.customer.portal.backend.assemble;

import in.co.ad.customer.portal.backend.domain.CustomerDo;
import in.co.ad.customer.portal.backend.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDetailsAssembler {

    public CustomerDo assembleCustomerDetailsDomain(CustomerDto customerDto) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDto, CustomerDo.class);
    }
    public CustomerDto assembleCustomerDetailsDto(CustomerDo customerDo) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDo, CustomerDto.class);
    }

    public List<CustomerDto> assembleCustomerDetailsDto(Iterable<CustomerDo> customerDos) {
        List<CustomerDto> customerDtos = new ArrayList<>();

        customerDos.forEach(customerDo -> customerDtos.add(assembleCustomerDetailsDto(customerDo)));

        return customerDtos;
    }

}
