package tests.application.queries.common.sortData;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import application.queries.common.sortData.SortTypeQueryData;
import application.queries.common.sortData.SortTypeQueryDataToSortTypeMapper;
import base.repository.SortType;

public class SortTypeQueryDataToSortTypeMapperUnitTests {
    
    @Test
    public void map_WhenAscending_ShouldReturnAscending() {
        SortTypeQueryDataToSortTypeMapper mapper = new SortTypeQueryDataToSortTypeMapper();
        
        SortType result = mapper.map(SortTypeQueryData.Ascending);
        
        assertThat(result).isEqualTo(SortType.Ascending);
    }
    
    @Test
    public void map_WhenDescending_ShouldReturnDescending() {
        SortTypeQueryDataToSortTypeMapper mapper = new SortTypeQueryDataToSortTypeMapper();
        
        SortType result = mapper.map(SortTypeQueryData.Descending);
        
        assertThat(result).isEqualTo(SortType.Descending);
    }
}
