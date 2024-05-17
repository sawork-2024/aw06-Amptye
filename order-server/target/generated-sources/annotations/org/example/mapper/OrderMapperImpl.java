package org.example.mapper;

import com.micropos.dto.OrderDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.Order;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public List<OrderDto> toOrderDtos(Collection<Order> orderCollection) {
        if ( orderCollection == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orderCollection.size() );
        for ( Order order : orderCollection ) {
            list.add( toOrderDto( order ) );
        }

        return list;
    }

    @Override
    public Collection<Order> toOrders(Collection<OrderDto> orderDtos) {
        if ( orderDtos == null ) {
            return null;
        }

        Collection<Order> collection = new ArrayList<Order>( orderDtos.size() );
        for ( OrderDto orderDto : orderDtos ) {
            collection.add( toOrder( orderDto ) );
        }

        return collection;
    }
}
