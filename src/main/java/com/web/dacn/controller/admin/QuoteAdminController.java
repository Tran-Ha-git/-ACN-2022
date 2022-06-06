package com.web.dacn.controller.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.quote.QuoteCategoryDto;
import com.web.dacn.dto.quote.QuoteDto;
import com.web.dacn.entity.book.BookCategory;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.quote.QuoteCategory;
import com.web.dacn.entity.user.Author;
import com.web.dacn.service.admin.AuthorService;
import com.web.dacn.service.client.CommentQuoteService;
import com.web.dacn.service.client.QuoteCategoryService;
import com.web.dacn.service.client.QuoteService;
import com.web.dacn.service.client.ReviewQuoteService;
import com.web.dacn.service.storage.StorageService;
import org.springframework.core.io.Resource;


@Controller
@RequestMapping("/admin/quote")
public class QuoteAdminController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteCategoryService quoteCategoryService;
	
	@Autowired
	private ReviewQuoteService reviewQuoteService;
	
	@Autowired
	private CommentQuoteService commentQuoteService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private StorageService storageService;
	
	@ModelAttribute("categories")
	public List<QuoteCategoryDto> getQuoteCategories() {
		return quoteCategoryService.findAll().stream().map((item) -> {
			QuoteCategoryDto quoteCategoryDto = new QuoteCategoryDto();
			BeanUtils.copyProperties(item, quoteCategoryDto);
			return quoteCategoryDto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serverFile(@PathVariable String filename) {
		storageService.setRootLocation("uploads/images/quote");
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_GIF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + file.getFilename() + "")
				.body(file);
	}
	
	@GetMapping("")
	public String get(@RequestParam(name = "q", required = false) String search, 
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "sort", required = false) String sort,
			ModelMap model,
			@RequestParam(name= "page") Optional<Integer> page, 
			@RequestParam(name= "size") Optional<Integer> size) {
		int pageSize = size.orElse(9);
		model.addAttribute("size", pageSize);
		int currentPage = page.orElse(1);
		Pageable pageable;
		if(StringUtils.hasText(sort)) {
			String field = sort.trim().split("_")[0];
			String type =  sort.trim().split("_")[1];
			if(type.contentEquals("desc")) {
				pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(field).descending());
			}else {
				pageable = PageRequest.of(currentPage-1, pageSize, Sort.by(field).ascending());
			}
			model.addAttribute("sort", sort);
		}else {
			pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("id").descending());
		}
		Page<Quote> results = null;
		if (StringUtils.hasText(category) && StringUtils.hasText(search)) {
			results = quoteService.findAllByContentContainingAndQuoteCategories_slug(search,category, pageable);
			model.addAttribute("search", search);
			model.addAttribute("category", category);
		}else if (StringUtils.hasText(search)) {
			results = quoteService.findByContentContaining(search, pageable);
			model.addAttribute("search", search);
		}else if (StringUtils.hasText(category)){
			results = quoteService.findAllByContentContainingAndQuoteCategories_slug("",category, pageable);
			model.addAttribute("category", category);
		}  else {
			results = quoteService.findAll(pageable);
		}
		int totalPages = results.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pages = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		} 
		model.addAttribute("results", results);
		return "adminListQuote";
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String editGet(ModelMap model, @PathVariable("id") Long id) {
		Optional<Quote> optional = quoteService.findById(id);
		if(optional.isPresent()) {
			Quote quote = optional.get();
			QuoteDto quoteDto = new QuoteDto();
			BeanUtils.copyProperties(quote, quoteDto);
			model.addAttribute("quoteDto", quoteDto);
			model.addAttribute("categoryList", quoteCategoryService.findAll());
			model.addAttribute("authorList", authorService.findAll());
		}
		return "adminEditQuote";
	}
	
	@PostMapping("/edit/{id}")
	public ModelAndView editPost(ModelMap model, @PathVariable("id") Long id, 
			@RequestParam("author") Optional<Long> optionalAuthor,
			@RequestParam("category") Optional<List<Long>> optionalCategory,
			@Valid @ModelAttribute("quoteDto") QuoteDto quoteDto,
			BindingResult result) {
		model.addAttribute("categoryList", quoteCategoryService.findAll());
		model.addAttribute("authorList", authorService.findAll());
		
		
		if(result.hasErrors()) {
			return new ModelAndView("adminEditQuote");
		}
		Optional<Quote> optional = quoteService.findById(id);
		
		List<Quote> list = quoteService.findBySlug(quoteDto.getSlug());
		if((optional.get().getSlug()==null || !optional.get().getSlug().equalsIgnoreCase(quoteDto.getSlug())) && list!=null && list.size() > 0 ) {
			result.addError(new FieldError("slug", "slug", "Slug already exists!"));
			return new ModelAndView("adminEditQuote");
		}
		
		if(optional.isPresent()) {
			Quote quote = optional.get();			
			quote.setContent(quoteDto.getContent());
			quote.setMetaTitle(quoteDto.getMetaTitle());
			quote.setMetaDescription(quoteDto.getMetaDescription());
			quote.setSlug(quoteDto.getSlug());
			
			if(optionalAuthor.isPresent()) {
				Author author = authorService.getById(optionalAuthor.get());
				quote.setAuthor(author);
			}
			
			if(quoteDto.getImageFile()!=null && !quoteDto.getImageFile().isEmpty()) {
				storageService.setRootLocation("uploads/images/quote");
				if (quote.getThumbnail()!=null && !quote.getThumbnail().isEmpty() && !quote.getThumbnail().toLowerCase().contains("dummyimage.com")) {
					try {
						storageService.delete(quote.getThumbnail());
					} catch (Exception e) {
						// TODO: handle exception
						result.addError(new FieldError("thumbnail", "thumbnail", "thumbnail error delete!"));
						return new ModelAndView("adminEditQuote");
					}
				}
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				quoteDto.setThumbnail(storageService.getStoredFileName(quoteDto.getImageFile(), uuString));
				try {
					storageService.store(quoteDto.getImageFile(), quoteDto.getThumbnail());
					quote.setThumbnail(quoteDto.getThumbnail());
				} catch (Exception e) {
					// TODO: handle exception
					result.addError(new FieldError("thumbnail", "thumbnail", "thumbnail error save!"));
					return new ModelAndView("adminEditQuote");
				}
			}
			
			if(optionalCategory.isPresent()) {
				Set<QuoteCategory> sets = new HashSet<>();
				for (Long item : optionalCategory.get()) {
					QuoteCategory quoteCategory = quoteCategoryService.getById(item);
					sets.add(quoteCategory);
				}
				quote.setQuoteCategories(sets);
			}
			quoteService.save(quote);
			return new ModelAndView("redirect:/admin/quote");
		}
		return new ModelAndView("adminEditQuote");
	}
	
	@GetMapping("/new")
	public String createGet(ModelMap model) {
		QuoteDto quoteDto = new QuoteDto();
		model.addAttribute("quoteDto", quoteDto);
		model.addAttribute("categoryList", quoteCategoryService.findAll());
		model.addAttribute("authorList", authorService.findAll());
		return "adminCreateQuote";
	}
	
	@PostMapping("/new")
	public ModelAndView createPost(ModelMap model,
			@RequestParam("author") Optional<Long> optionalAuthor,
			@RequestParam("category") Optional<List<Long>> optionalCategory,
			@Valid @ModelAttribute("quoteDto") QuoteDto quoteDto,
			BindingResult result) {
		model.addAttribute("categoryList", quoteCategoryService.findAll());
		model.addAttribute("authorList", authorService.findAll());
		
		if(result.hasErrors()) {
			return new ModelAndView("adminCreateQuote");
		}
		
		List<Quote> list = quoteService.findBySlug(quoteDto.getSlug());
		if(list!=null && list.size() > 0 ) {
			result.addError(new FieldError("slug", "slug", "Slug already exists!"));
			return new ModelAndView("adminCreateQuote");
		}
		
		Quote quote = new Quote();			
		quote.setContent(quoteDto.getContent());
		quote.setMetaTitle(quoteDto.getMetaTitle());
		quote.setMetaDescription(quoteDto.getMetaDescription());
		quote.setSlug(quoteDto.getSlug());
		
		if(optionalAuthor.isPresent()) {
			Author author = authorService.getById(optionalAuthor.get());
			quote.setAuthor(author);
		}
		
		if(quoteDto.getImageFile()!=null && !quoteDto.getImageFile().isEmpty()) {
			storageService.setRootLocation("uploads/images/quote");
			if (quote.getThumbnail()!=null && !quote.getThumbnail().isEmpty() && !quote.getThumbnail().toLowerCase().contains("dummyimage.com")) {
				try {
					storageService.delete(quote.getThumbnail());
				} catch (Exception e) {
					// TODO: handle exception
					result.addError(new FieldError("thumbnail", "thumbnail", "thumbnail error delete!"));
					return new ModelAndView("adminEditQuote");
				}
			}
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			quoteDto.setThumbnail(storageService.getStoredFileName(quoteDto.getImageFile(), uuString));
			try {
				storageService.store(quoteDto.getImageFile(), quoteDto.getThumbnail());
				quote.setThumbnail(quoteDto.getThumbnail());
			} catch (Exception e) {
				// TODO: handle exception
				result.addError(new FieldError("thumbnail", "thumbnail", "thumbnail error save!"));
				return new ModelAndView("adminEditQuote");
			}
		}
		
		if(optionalCategory.isPresent()) {
			Set<QuoteCategory> sets = new HashSet<>();
			for (Long item : optionalCategory.get()) {
				QuoteCategory quoteCategory = quoteCategoryService.getById(item);
				sets.add(quoteCategory);
			}
			quote.setQuoteCategories(sets);
		}
		quote.setMod_time(new Date());
		quoteService.save(quote);
		return new ModelAndView("redirect:/admin/quote");
	}
	
	@GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, 
    		@RequestHeader(value = "referer", required = false) String referer) {
    	Optional<Quote> optional = quoteService.findById(id);
		if(optional.isPresent()) {
			quoteService.delete(optional.get());
		}
    	return new ModelAndView("redirect:" + referer);
    }
	
	@PostMapping("media/upload")
	@ResponseBody
	public String media() {
		return "Upload success";
	}
}
