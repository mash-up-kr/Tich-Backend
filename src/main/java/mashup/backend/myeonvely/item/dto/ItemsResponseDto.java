package mashup.backend.myeonvely.item.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemsResponseDto {

    private List<ItemResponseDto> itemResponseDtos;

    @Builder
    public ItemsResponseDto(List<ItemResponseDto> itemResponseDtos) {
        this.itemResponseDtos = itemResponseDtos;
    }
}
