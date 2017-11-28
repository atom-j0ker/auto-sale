package com.auto.controllers;

import com.auto.crud.AutoDAO;
import com.auto.crud.BrandDAO;
import com.auto.crud.ModelDAO;
import com.auto.crud.UsersDAO;
import com.auto.model.Auto;
import com.auto.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@Controller
public class AutoController extends HttpServlet {

    @Autowired
    private AutoDAO autoDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping(value = "/all-announcements", method = RequestMethod.GET)
    public String allAnnouncements(Model model) {
        model.addAttribute("autos", autoDAO.getAutoList());
        return "all-announcements";
    }

    @RequestMapping(value = "/announcements", method = RequestMethod.GET)
    public String announcementsBySearch(@RequestParam(value = "brandId", required = false) long brandId,
                                        @RequestParam(value = "modelId", required = false) long modelId,
                                        @RequestParam(value = "yearFrom", required = false) String yearFrom,
                                        @RequestParam(value = "yearTo", required = false) String yearTo,
                                        @RequestParam(value = "mileageFrom", required = false) String mileageFrom,
                                        @RequestParam(value = "mileageTo", required = false) String mileageTo,
                                        @RequestParam(value = "volumeFrom", required = false) String volumeFrom,
                                        @RequestParam(value = "volumeTo", required = false) String volumeTo,
                                        @RequestParam(value = "transmission", required = false) String transmission,
                                        @RequestParam(value = "priceFrom", required = false) String priceFrom,
                                        @RequestParam(value = "priceTo", required = false) String priceTo,
                                        @RequestParam(value = "sortBy", required = false) String sortBy,
                                        Model model) {

        model.addAttribute("autos", autoDAO.getAutoListBySearch(String.valueOf(brandId), String.valueOf(modelId), yearFrom, yearTo,
                mileageFrom, mileageTo, volumeFrom, volumeTo, transmission, priceFrom, priceTo, sortBy));

        return "all-announcements";
    }

    @RequestMapping(value = "/auto-announcement/{autoId}", method = RequestMethod.GET)
    public String autoAnnouncement(@PathVariable("autoId") long autoId, Model model) {
        Auto auto = autoDAO.get(autoId);
        model.addAttribute("auto", auto);
        return "auto-announcement";
    }

    @RequestMapping(value = "/announcement-search", method = RequestMethod.GET)
    public String announcementSearch(Model model) {
        model.addAttribute("brands", brandDAO.getBrandList());
        return "/announcement-search";
    }

    @RequestMapping(value = "/announcement-search", method = RequestMethod.POST)
    public String announcementSearchGo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String brandName = request.getParameter("brandComboBoxName");
        String modelName = request.getParameter("modelComboBoxName");
        String yearFrom = request.getParameter("yearFromComboBoxName");
        String yearTo = request.getParameter("yearToComboBoxName");
        String mileageFrom = request.getParameter("mileageFrom");
        String mileageTo = request.getParameter("mileageTo");
        String volumeFrom = request.getParameter("volumeFrom");
        String volumeTo = request.getParameter("volumeTo");
        String transmission = request.getParameter("TransmissionComboBoxName");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");

        long brandId = 0;
        long modelId = 0;

        if (!brandName.equals("0")) {
            brandId = brandDAO.getBrandIdByBrandName(brandName);
            if (!(modelName.equals("0")))
                modelId = modelDAO.getModelIdByModelName(modelName, brandId);
        }

        redirectAttributes.addAttribute("brandId", brandId);
        redirectAttributes.addAttribute("modelId", modelId);
        redirectAttributes.addAttribute("yearFrom", yearFrom);
        redirectAttributes.addAttribute("yearTo", yearTo);
        redirectAttributes.addAttribute("mileageFrom", mileageFrom);
        redirectAttributes.addAttribute("mileageTo", mileageTo);
        redirectAttributes.addAttribute("volumeFrom", volumeFrom);
        redirectAttributes.addAttribute("volumeTo", volumeTo);
        redirectAttributes.addAttribute("transmission", transmission);
        redirectAttributes.addAttribute("priceFrom", priceFrom);
        redirectAttributes.addAttribute("priceTo", priceTo);
        redirectAttributes.addAttribute("sortBy", "0");

        return "redirect:/announcements";
    }

    @RequestMapping(value = "/announcement-add", method = RequestMethod.GET)
    public String announcementAddForm(Model model) {
        model.addAttribute("brands", brandDAO.getBrandList());
        return "announcement-add";
    }

    @RequestMapping(value = "/announcement-add", method = RequestMethod.POST)
    public String announcementAddToDB(@RequestParam("file") MultipartFile[] files, HttpSession session, HttpServletRequest request, Auto auto, Brand brand, com.auto.model.Model brandModel) {

        String brandName = request.getParameter("brandComboBoxName");
        String modelName = request.getParameter("modelComboBoxName");
        int year = Integer.parseInt(request.getParameter("yearComboBoxName"));
        int mileage = Integer.parseInt(request.getParameter("mileage"));
        double volume = Double.parseDouble(request.getParameter("volume"));
        int cylinders = Integer.parseInt(request.getParameter("cylinders"));
        String transmission = request.getParameter("transmissionComboBoxName");
        int gears = Integer.parseInt(request.getParameter("gears"));
        String fuel = request.getParameter("fuelComboBoxName");
        String driveTrain = request.getParameter("driveTrainComboBoxName");
        String color = request.getParameter("colorComboBoxName");
        int price = Integer.parseInt(request.getParameter("price"));
        long userId = Long.valueOf(session.getAttribute("userId").toString());

        long brandId = brandDAO.getBrandIdByBrandName(brandName);
        long modelId = modelDAO.getModelIdByModelName(modelName, brandId);

        brand.setId(brandId);
        brand.setName(brandName);

        brandModel.setId(modelId);
        brandModel.setName(modelName);

        auto.setBrand(brand);
        auto.setModel(brandModel);
        auto.setYear(year);
        auto.setMileage(mileage);
        auto.setVolume(volume);
        auto.setCylinders(cylinders);
        auto.setTransmission(transmission);
        auto.setGears(gears);
        auto.setFuel(fuel);
        auto.setDriveTrain(driveTrain);
        auto.setColor(color);
        auto.setPrice(price);
        auto.setImages(files.length);
        auto.setUsers(usersDAO.get(userId));

        long autoId = autoDAO.add(auto);

        String fileName = null;
        String msg = "";
        File directory = new File("/Users/Sanek/Documents/IdeaProjects/AutoSale/src/main/webapp/resources/image/" + autoId);
        if (!directory.exists())
            directory.mkdir();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(directory + "/" + (i + 1) + ".jpg")));
                    System.out.println(directory);
                    buffStream.write(bytes);
                    buffStream.close();
                } catch (Exception e) {
                    System.out.println("You failed to upload " + fileName + ": " + e.getMessage());
                }
            }
        }

        return "redirect:/all-announcements";
    }

    @RequestMapping(value = "/auto-announcement/edit/{autoId}", method = RequestMethod.GET)
    public String announcementEdit(@PathVariable("autoId") long autoId, Model model) {
        Auto auto = autoDAO.get(autoId);
        model.addAttribute("auto", auto);
        model.addAttribute("brands", brandDAO.getBrandList());
        return "announcement-edit";
    }

    @RequestMapping(value = "/auto-announcement/edit/{autoId}", method = RequestMethod.POST)
    public String announcementEditInDB(HttpSession session, HttpServletRequest request, @PathVariable("autoId") long autoId,
                                       Auto auto, BindingResult result) {
        if (result.hasErrors()) {
            return "announcement-edit";
        } else {

            String brandName = request.getParameter("brandComboBoxName");
            String modelName = request.getParameter("modelComboBoxName");
            int year = Integer.parseInt(request.getParameter("year"));
            int mileage = Integer.parseInt(request.getParameter("mileage"));
            double volume = Double.parseDouble(request.getParameter("volume"));
            int cylinders = Integer.parseInt(request.getParameter("cylinders"));
            String transmission = request.getParameter("transmission");
            int gears = Integer.parseInt(request.getParameter("gears"));
            String fuel = request.getParameter("fuel");
            int price = Integer.parseInt(request.getParameter("price"));
            long userId = Long.valueOf(session.getAttribute("userId").toString());

            System.out.println(brandName + " " + modelName + " " + year + " " + mileage + " " + volume + " " + cylinders + " "
                    + transmission + " " + gears + " " + fuel + " " + price);

            long brandId = brandDAO.getBrandIdByBrandName(brandName);
            long modelId = modelDAO.getModelIdByModelName(modelName, brandId);

            auto.setId(autoId);
            auto.setBrand(brandDAO.get(brandId));
            auto.setModel(modelDAO.get(modelId));
            auto.setYear(year);
            auto.setMileage(mileage);
            auto.setVolume(volume);
            auto.setCylinders(cylinders);
            auto.setTransmission(transmission);
            auto.setGears(gears);
            auto.setFuel(fuel);
            auto.setPrice(price);
            auto.setUsers(usersDAO.get(userId));

            autoDAO.update(auto);
            return "redirect:/all-announcements";
        }
    }

    @RequestMapping(value = "/auto-announcement/delete/{autoId}", method = RequestMethod.POST)
    public String announcementDeleteFromDB(@PathVariable("autoId") long autoId) {
        this.autoDAO.delete(autoId);
        return "redirect:/all-announcements";
    }

    @RequestMapping(value = "/announcement-add/modelsList", method = RequestMethod.POST)
    public
    @ResponseBody
    String modelsList(HttpServletRequest request) {
        long brandId = 0;
        String modelString = "";
        String brandName = request.getParameter("brandName");
        if (!brandName.equals("0")) {
            brandId = brandDAO.getBrandIdByBrandName(brandName);
            List<com.auto.model.Model> modelList = modelDAO.getModelListByBrand(brandId);
            for (com.auto.model.Model ml : modelList)
                modelString += ml.getName() + ",";
            if (modelString.endsWith(","))
                modelString = modelString.substring(0, modelString.length() - 1);
        }
        return modelString;
    }

    @RequestMapping(value = "/admin-page", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("brands", brandDAO.getBrandList());
        return "/admin-page";
    }

    @RequestMapping(value = "/brand-add", method = RequestMethod.POST)
    public String brandAdd(HttpServletRequest request, Brand brand) {
        String brandName = request.getParameter("brand");

        if (!brandName.equals("")) {
            brand.setName(brandName);
            brandDAO.add(brand);
        }

        return "redirect:/admin-page";
    }

    @RequestMapping(value = "/model-add", method = RequestMethod.POST)
    public String modelAdd(HttpServletRequest request, Brand brand, com.auto.model.Model brandModel) {
        String brandName = request.getParameter("brandComboBoxName");
        String modelName = request.getParameter("model");

        if (!brandName.equals("0") && !modelName.equals("")) {
            long brandId = brandDAO.getBrandIdByBrandName(brandName);

            brand.setId(brandId);
            brand.setName(brandName);

            brandModel.setName(modelName);
            brandModel.setBrand(brand);

            modelDAO.add(brandModel);
        }

        return "redirect:/admin-page";
    }
}
