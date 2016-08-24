package com.jianglibo.vaadin.dashboard.data.container;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.collect.Maps;
import com.jianglibo.vaadin.dashboard.data.ClassNameAndId;
import com.jianglibo.vaadin.dashboard.data.EntityCacheWrapper;
import com.jianglibo.vaadin.dashboard.domain.BaseEntity;
import com.jianglibo.vaadin.dashboard.domain.Box;
import com.jianglibo.vaadin.dashboard.domain.Domains;
import com.jianglibo.vaadin.dashboard.repositories.RepositoryCommonMethod;
import com.vaadin.data.Buffered;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Container.ItemSetChangeNotifier;
import com.vaadin.data.Container.PropertySetChangeNotifier;
import com.vaadin.data.Container.Sortable;
import com.vaadin.data.util.filter.UnsupportedFilterException;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FreeContainer<T extends BaseEntity> implements Indexed, Sortable, ItemSetChangeNotifier, PropertySetChangeNotifier, Buffered,
		Container.Filterable, Serializable {
 
	private int perPage;

	private boolean trashed;

	private EventBus eventBus;

	private String filterStr;

	private int currentPage;

	private Sort sort;

	private final Domains domains;

	private boolean enableSort = false;

	private Table table;

	private Class<T> clazz;
	
	private String simpleClassName;
	
	private Integer cachedSize;
	
	private BiMap<Integer, ClassNameAndId> indexAndOid = HashBiMap.create();
	
	private EntityCacheWrapper entityCacheWrapper;
	
	public FreeContainer(Class<T> clazz, Domains domains, EntityCacheWrapper entityCacheWrapper) {
		this.clazz = clazz;
		this.simpleClassName = clazz.getSimpleName();
		this.domains = domains;
		this.entityCacheWrapper = entityCacheWrapper;
	}

	@Override
	public T nextItemId(Object itemId) {
		return null;
	}

	@Override
	public Object prevItemId(Object itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Object firstItemId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object lastItemId() {
		return null;
	}

	@Override
	public boolean isFirstId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLastId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object addItemAfter(Object previousItemId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item addItemAfter(Object previousItemId, Object newItemId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(Object itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getContainerPropertyIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getItemIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property getContainerProperty(Object itemId, Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getType(Object propertyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		if (cachedSize == null) {
			return new Long(domains.getRepositoryCommonMethod(simpleClassName).countByArchivedEquals(trashed)).intValue();
		}
		return cachedSize;
	}

	@Override
	public boolean containsId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item addItem(Object itemId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addItem() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeItem(Object itemId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addContainerFilter(Filter filter) throws UnsupportedFilterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContainerFilter(Filter filter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllContainerFilters() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Filter> getContainerFilters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() throws SourceException, InvalidValueException {
		// TODO Auto-generated method stub

	}

	@Override
	public void discard() throws SourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBuffered(boolean buffered) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBuffered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertySetChangeListener(PropertySetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(PropertySetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertySetChangeListener(PropertySetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(PropertySetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addItemSetChangeListener(ItemSetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListener(ItemSetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeItemSetChangeListener(ItemSetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(ItemSetChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sort(Object[] propertyId, boolean[] ascending) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<?> getSortableContainerPropertyIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOfId(Object itemId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getIdByIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getItemIds(int startIndex, int numberOfItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addItemAt(int index) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item addItemAt(int index, Object newItemId) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}
}