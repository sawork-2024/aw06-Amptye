package org.example.mapper;

import com.micropos.dto.ItemDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.Item;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public List<ItemDto> toItemDtos(Collection<Item> itemCollection) {
        if ( itemCollection == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( itemCollection.size() );
        for ( Item item : itemCollection ) {
            list.add( toItemDto( item ) );
        }

        return list;
    }

    @Override
    public Collection<Item> toItems(Collection<ItemDto> itemDtos) {
        if ( itemDtos == null ) {
            return null;
        }

        Collection<Item> collection = new ArrayList<Item>( itemDtos.size() );
        for ( ItemDto itemDto : itemDtos ) {
            collection.add( toItem( itemDto ) );
        }

        return collection;
    }
}
