package application.queries.common.sortData;

import base.repository.SortType;
import base.utils.Mapper;

public class SortTypeQueryDataMapper
		implements
			Mapper<SortTypeQueryData, SortType> {

	@Override
	public SortType map(SortTypeQueryData from) {
		switch (from) {
			case Ascending :
				return SortType.Ascending;
			case Descending :
				return SortType.Descending;
			default :
				throw new IllegalArgumentException("Unknown enum: " + from);
		}
	}

}
