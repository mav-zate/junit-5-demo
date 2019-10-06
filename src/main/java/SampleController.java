public class SampleController {
    @Path("/sample_route_1")
    public Response firstSampleRoute() {
        Response response = new Response();
        response.setStatus(200);
        return response;
    }

    @Path("/sample_route_2")
    public Response secondSampleRoute() {
        Response response = new Response();
        response.setStatus(200);
        return response;
    }

    @Path("/sample_route_3")
    public Response thirdSampleRoute() {
        Response response = new Response();
        response.setStatus(200);
        return response;
    }

//    @Path("/sample_route_4")
//    public Response fourthSampleRoute() {
//        Response response = new Response();
//        response.setStatus(200);
//        return response;
//    }
//
//    @Path("/sample_route_5")
//    public Response fifthSampleRoute() {
//        Response response = new Response();
//        response.setStatus(200);
//        return response;
//    }
}
