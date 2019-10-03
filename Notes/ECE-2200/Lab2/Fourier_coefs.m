function coefs = Fourier_coefs(T_o, x, K)
% Returns a (2K + 1)-dimensional row vector coefs of Fourier coefficients
% of a signal with fundamental period T_o sampled over the interval
% [0, T_o] every T_o / N seconds, where N = 44,100. The samples appear
% in the row vector x. The sample at time 0 is the leftmost element of x,
% and the sample at time T_o is the rightmost. The vector of coefficients
% coefs lists the Fourier coefficients a _k keft to right as k runs
% from -K to K.
f_o = 1 / T_o;
T_s = 1 / 44100;
N = 44100;
indices = [-K:K];
times = [0:length(x) - 1];
base = exp(-j*2*pi*f_o*T_s);
W = (base.^(-indices'*times))/N;
coefs = x*W';